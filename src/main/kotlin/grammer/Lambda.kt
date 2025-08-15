package grammer// 자바는 형식을 중요시함
// 예를 들어, 람다에 있어서 자바는 항상 함수형 인터페이스를 강제하고
// 반환 타입 람다에서 블록을 쓰면 return이 요구됨

// 반면 코틀린은 표현을 중요시함
// 람다는 그 자체가 값으로 취급됨
// 블록 형태 역시 지원되며 마지막 라인이 자연스럽게 return 적용
// 만약 Unit(무의미한 값)이어도 그냥 흘려보내면 끝

/**
 * lambda는 중괄호 안에 꼭 적을 것
 */

fun main() {
    val add: (Int, Int) -> Int = { a, b -> a + b } // 코틀린은 블록으로 감싸네...

    add(1, 2) // 사이드 이펙트용 흘려보내기
    val result = add(5, 10)
    println("result : ${result}")

    // 자바에는 없는 문제점이 코틀린에 있다
    // 람다식 내부에서 return 하면 람다만 종료되던 자바와 달리, 코틀린은 non lexical 이슈가 있다
    // 람다의 return 으로 인해 전체 프로세스 종료 이슈 가능

    val numbers = listOf(1, 2, 3, 4, 5)
    numbers.forEach {
        // non lexical 이슈 발생 가능
        if (it == 3) return@forEach // forEach 람다 내부에서만 종료, 바깥 함수는 계속 진행
        println(it)
    }
    println("끝까지 왔나요?")

    println(upperFun("hello"))

    val upperLambda = {text: String -> text.uppercase()}
    println(upperLambda("hello"))

    // -> 없이 쓰는 람다, 파라미터가 없는 람다
    val printer = {println("no parameter")}
    printer()

    // 람다니까 다른 함수의 콜백 파라미터로 전달도 가능
    val nums = listOf(1, -2, 3, -4, 5, 6)
    val positive = nums.filter({x -> x > 0})
    val isNegative = {x: Int -> x < 0}
    val negative = nums.filter(isNegative)
    println(positive)
    println(negative)

    val doubled = nums.map { x -> x * 2 }

    val isTripled = { x: Int -> x * 3 }
    val tripled = nums.map(isTripled)

    println(doubled)
    println(tripled)

    // https://kotlinlang.org/docs/kotlin-tour-functions.html#function-types
    // 함수 타입
    val plusOne: (Int) -> Int = {x -> x + 1}
    val unitPrint: () -> Unit = {-> println("unit return type")}

    println(plusOne(1))
    unitPrint()

    // 매개변수 및 반환 유형을 람다 표현식 또는 함수 유형으로 선언해야 합니다.
    // 그렇지 않으면 컴파일러가 람다 표현식이 어떤 유형인지 알 수 없습니다.(타입 추론의 한계)
    // 예를 들어 다음은 작동하지 않습니다(Cannot infer a type for this parameter. Please specify it explicitly.)
//    val upperCaseString = { str -> str.uppercase() }

    val times = listOf(12, 2, 10, 5, 59)
    println("전체 분 총합시간은 ${times.map(toSeconds("minute")).sum()}")

    // The initial value is zero.
    // The operation sums the initial value with every item in the list cumulatively.
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item })) // 6

    // Alternatively, in the form of a trailing lambda(후행 람다)
    // 함수가 호출하는 함수를 중괄호로 분리하여 가독성 업
    // 함수의 마지막 파라미터가 함수이면, 람다 표현식을 괄호 밖에 배치할 수 있다
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item})) // 6
    println(listOf(1, 2, 3).fold(0) { x, item -> x + item })  // 6(후행 람다)
}

fun upperFun(text: String): String {
    return text.uppercase()
}


// 람다를 반환하는 함수도 가능
fun toSeconds(time: String): (Int) -> Int = when (time) {
    "minute" -> {value -> value * 60}
    "hour" -> {value -> value * 60 * 60}
    "second" -> {value -> value}
    else -> {value -> value}
}

// https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions
// 나중에 참조해보기