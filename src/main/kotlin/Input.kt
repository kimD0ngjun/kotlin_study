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
