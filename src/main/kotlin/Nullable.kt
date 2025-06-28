fun main() {
//    // input warning! NPE!
//    problem()
//
//    // input is safe... but variable can be still null...
//    preventAssignNull()
//
//    // compiler may know variable is not null... but it is still dangerous
//    convinceCompiler()
//
    // Elvis Operator is safe from NPE
    doElvisOperating()

    // ?. 연산자는 null 안전성을 확보하기 위한 연산자이지, 결국 끝까지 개발자가 책임져야 하는듯?
    doQuestionMark()
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

fun common(): Int? {
    val inputString = input()

    // int or null
    return inputString.toIntOrNull()
}

fun preventAssignNull() {
    // int or null
    val inputValue = common()
    print("your input is $inputValue") // prevent Null Exception
}

fun convinceCompiler() {
    // int or null
    val inputValue = common()
    print("your input is $inputValue") // prevent Null Exception

    // Compiler will recognize 'isEven' might not be null
    val isEven = inputValue!! % 2 == 0
    print("your input is even : $isEven")
}

/**
 * important
 */
fun doElvisOperating() {
    val inputString = input()

    // Elvis Operator
    // if inputInt is null, inputInt is 0(default value)
    val inputInt = inputString.toIntOrNull() ?: 0
    println("your input is $inputInt")

    val isEven = inputInt % 2 == 0
    print("this is even : $isEven")
}

fun doQuestionMark() {
    val inputString = input()

    // if inputInt is int, +1
    // ?. -> 널 안전성을 보장해주는 코틀린 문법
//    val inputInt = inputString.toIntOrNull()?.inc() // 물론 얘만으론 추가 연산에서 Null을 막지 못함

    // if inputInt is int, %2
    val inputInt = inputString.toIntOrNull()?.rem(2) // 역시 얘만으로도 추가 연산 Null을 막지 못함
    println("your input is $inputInt")
}