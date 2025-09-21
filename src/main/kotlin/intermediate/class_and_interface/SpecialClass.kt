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

fun main() {
    println(hello(Human("김동준", "백수")))
}