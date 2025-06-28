fun main() {
//    // input warning! NPE!
//    problem()
//
//    // input is safe... but variable can be still null...
//    preventAssignNull()
//
//    // compiler may know variable is not null... but it is still dangerous
//    convinceCompiler()

    // Elvis Operator is safe from NPE
    getReplacement()
}

fun input(): String {
    print("input string : ")
    return readln()
}

fun problem() {
    val inputString = input()

    // only int
    val inputInt = inputString.toInt()
    print("your input is $inputInt") // it's possible to occur exception
}

fun preventAssignNull() {
    val inputString = input()

    // int or null
    val inputValue = inputString.toIntOrNull()
    print("your input is $inputValue") // prevent Null Exception
}

fun convinceCompiler() {
    val inputString = input()

    // int or null
    val inputValue = inputString.toIntOrNull()
    print("your input is $inputValue") // prevent Null Exception

    // Compiler will recognize 'isEven' might not be null
    val isEven = inputValue!! % 2 == 0
    print("your input is even : $isEven")
}

fun getReplacement() {
    val inputString = input()

    // Elvis Operator
    // if inputInt is null, inputInt is 0(default value)
    val inputInt = inputString.toIntOrNull() ?: 0
    println("your input is $inputInt")

    val isEven = inputInt % 2 == 0
    print("this is even : $isEven")
}
