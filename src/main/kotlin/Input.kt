import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    // basic kotlin input
    print("input : ")
    val x = readln()

    println("your input value is ${x}, right?")
    val y = readln()

    if (y.equals("y", true)) {
        println("good!")
    } else {
        println("sad :(")
    }

    // java BufferedReader
    val brInput = BufferedReader(InputStreamReader(System.`in`))
    print("this is buffered reader input : ")
    val line = brInput.readLine()

    println("your buffered reader input is ${line}")

    // input is String
    print("input string : ")
    val inputString = readln()

//    val isEven = inputString % 2 == 0 // wrong because of type

    val inputInt = inputString.toInt()
    val isEven = inputInt % 2 == 0

    println("your input value is even : ${isEven}")
}
