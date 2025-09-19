package intermediate.object_declaration

// https://kotlinlang.org/docs/kotlin-tour-intermediate-objects.html#data-objects

// data object
/**
 * 자바의 레코드처럼 코틀린에 있던 데이터 클래스(toString과 equals, hashcode, copy 등등 간편히 제공해주는 거)
 * 객체 선언에서 봤던 것처럼 데이터 객체 선언이란 것도 존재
 * 얘는 단, 객체 선언이 단일 인스턴스이기 때문에 copy는 오버라이딩 안됨
 */
data object AppConfig {
    var name: String = "코틀린 애플리케이션"
    var version: String = "1.0.0"
}

// companion object
/**
 * 코틀린에는 동반 객체(companion object)라는 게 있음
 * 얘 알기 전에, 일단 코틀린에는 자바의 static 키워드가 존재하지 않음
 * 그 역할을 저 동반 객체가 대신해주는 것
 *
 * 이름이 동반 객체라는 것은, 얘는 무조건 클래스 내에서만 선언해야 된다.
 * static 역할을 하는, 즉 클래스명으로 접근할 수 있는 '익명 객체'를 선언하는 것
 */
class Example {
    // 클래스 내에서 유일하게 하나여야만 한다
    companion object ExampleCompanion {
        fun printName(): Unit = println("내 이름은 김동준")
    }
}

interface ExInterface {
    fun printName(): Unit
}

class Example2 {
    // 익명 객체라서 상속과 구현도 가능하다
    // 익명 객체라서 이름이 없어도 된다. 없을 때의 접근은 'Companion'이다.
    companion object: ExInterface {
        override fun printName(): Unit = println("내 이름은 김동준2")
    }
}

fun main() {
    println(AppConfig) // AppConfig
    println(AppConfig.name) // 코틀린 애플리케이션
    println(AppConfig.version) // 1.0.0

    Example.ExampleCompanion.printName()
    Example2.Companion.printName()

    // 물론 익명객체 이름 없이도 접근 가능(편하자라는 코틀린의 철학)
    Example.printName()
    Example2.printName()
}