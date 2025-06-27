import kotlin.jvm.Throws

fun main() {
    print("input : ")
    val x = readln()

    println("your input value is ${x}, right?")
    val y = readln()

    if (y.equals("y", true)) {
        println("good!")
    } else {
        println("sad :(")
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