fun main() {
    print("input string : ")
    val inputString = readln()

    // only int
    val inputInt = inputString.toInt()
    print("your input is ${inputInt}") // it's possible to occur exception

    // int or null
    val inputValue = inputString.toIntOrNull()
    print("your input is ${inputValue}") // prevent Null Exception
}