fun main() {
    print("input string : ")
    val inputString = readln()

    // only int
    val inputInt = inputString.toInt()
    print("your input is $inputInt") // it's possible to occur exception

    // int or null
    val inputValue = inputString.toIntOrNull()
    print("your input is $inputValue") // prevent Null Exception

    // inputValue can be assigned Null, then this code is wrong
//    val isEven = inputValue % 2 == 0

    // Compiler will recognize 'isEven' might not be null
    val isEven = inputValue!! % 2 == 0
    print("your input is even : $isEven")
}