package intermediate.class_and_interface

// https://kotlinlang.org/docs/kotlin-tour-intermediate-open-special-classes.html#special-classes

/**
 * 자바의 sealed 키워드는 상속 불허하는 키워드
 * 코틀린은 '상속 범위를 제한하는 키워드'
 * 코틀린의 sealed는 '같은 패키지 내에서만 상속을 가능케 하는' 키워드
 * 당연히 abstract처럼 인스턴스화는 불가
 * open이 제일 넓고, 그다음에 abstract, 그다음에 sealed, 그다음에 그냥 클래스
 */

sealed class Mammal(val name: String)

// 부모 클래스의 속성명과 자식 클래스 속성명 다르게 적자...
class Human(val humanName: String, val job: String): Mammal(humanName)
class Cat(val catName: String): Mammal(catName)

fun hello(mammal: Mammal): String {
    // when 안에서는 오버라이딩이 아니라 실제 타입 검증 하고 처리하는 것임
    // 그래서 각 타입별 멤버에게 접근이 가능함
    // 전통적인 동적 바인딩 기반 다형성이라기보단 패턴 매칭 기반의 타입 분기(스마트 캐스트)
    when (mammal) {
        is Human -> return "난 사람인 ${mammal.humanName}이고, 직업은 ${mammal.job}이야"
        is Cat -> return "난 고양이인 ${mammal.catName}이야"
        is HumanExample -> return "난 그냥 예시야"
    }
}

/**
 * 자바에도 열거형 enum이 있듯, 코틀린에도 있음
 * 단 자바에서 Enum과 Class가 다르게 취급된 것과 달리, 코틀린은 enum 키워드로 존재
 * 동작 매커니즘은 똑같다. 심지어 자바 Enum으로 싱글톤 인스턴스 만드는 게 코틀린 enum class도 같음
 * 얘 역시 인스턴스는 못 만듦, 하지만 생성자는 가진다
 */
enum class State(val state: String) {
    // enum class가 로딩될 때 딱 한번 로딩되고
    // 인스턴스가 이 시점에 열거형 필드 갯수(여기서는 3개)만큼 생김
    IDLE("게으름"), RUNNING("뜀"), RIDING("탐");

    fun introduce(): Unit = println("나는 현재 $state 상태다.")
}

/**
 * 코드를 작성할 때 가끔 클래스로부터 작은 객체를 생성하여 잠시만 사용해야 할 때가 있음
 * 예를 들면 토큰 인증 방식에서 뭐가 엑세스 토큰이고 뭐가 리프레시 토큰인지 감싸기 위한..?
 * 근데 지속적으로 이렇게 접근하면 성능에 영향을 미칠 수 있음
 * value class(구 inline class)는 이러한 성능 저하를 피하는 특수한 유형의 클래스
 * 다만, 오로지 한 개의 값 필드만 포함 가능
 */
@JvmInline
value class AccessToken(val token: String)
@JvmInline
value class RefreshToken(val token: String)

fun main() {
    println(hello(Human("김동준", "백수")))

    /**
     * enum class의 생성자는 외부 호출용이 아닌 enum 상수 정의할 때 내부저으로 호출되는 용도
     */
//    val state = State("배고픈")
    val idleState = State.IDLE
    idleState.introduce() // 이미 클래스 로딩될 때 생성된 내부 인스턴스를 참조하는 것에 불과
    val runningState = State.RUNNING
    runningState.introduce() // 이미 클래스 로딩될 때 생성된 내부 인스턴스를 참조하는 것에 불과
    val ridingState = State.RIDING
    ridingState.introduce() // 이미 클래스 로딩될 때 생성된 내부 인스턴스를 참조하는 것에 불과

    // 생명주기는 자바의 원시 타입과 거의 흡사하다
    // 값 전달 되는 것처럼 처리되며, 스택에 올라갔다 내려가기 때문에 GC의 힙 정리 대상조차 아님
    // 컬렉션 요소로 쓰이면 최소한의 래핑은 생기긴 하지만 그래도 오버헤드 최소화
    val accessToken = AccessToken("12345")
    val refreshToken = RefreshToken("1a2s3f")
    println("엑세스토큰 값: $accessToken \n리프레시토큰 값: $refreshToken")
}

/**
 * 참고로, 코틀린은 겉보기로는 타입이 오로지 클래스처럼 다뤄짐(원시, 참조 구별 x)
 * Int -> int
 * Double -> double
 * Boolean -> boolean
 * String -> String(객체)
 * nullable Int인 Int? -> Integer(객체, null 허용을 위한 박싱)
 */