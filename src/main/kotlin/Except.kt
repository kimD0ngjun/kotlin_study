import kotlin.jvm.Throws

fun main() {
    ex1()

    try {
        ex2()
    } catch (ex: Exception) {
        println("ex2 message: ${ex.message}")
    }

    try {
        ex3()
    } catch (ex: java.lang.NullPointerException) {
        println(ex.message)
    }

    try {
        ex4()
    } catch (ex: NullPointerException) {
        println(ex.message)
    }
}

fun ex1() {
    try {
        val result = 5 / 0
    } catch (ex: Exception) {
        println("ex1 message: ${ex.message}")
    }
}

@Throws
fun ex2(): Int {
    return 5 / 0
}

@Throws(java.lang.NullPointerException::class) // 클래스 참조 문법
fun ex3() {
    throw NullPointerException("자바의 널이랍니다")
}

@Throws(NullPointerException::class) // 클래스 참조 문법
fun ex4() {
    throw NullPointerException("코틀린의 널이랍니다")
}