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
}