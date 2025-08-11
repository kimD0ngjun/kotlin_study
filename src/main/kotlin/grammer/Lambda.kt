package grammer// 자바는 형식을 중요시함
// 예를 들어, 람다에 있어서 자바는 항상 함수형 인터페이스를 강제하고
// 반환 타입 람다에서 블록을 쓰면 return이 요구됨

// 반면 코틀린은 표현을 중요시함
// 람다는 그 자체가 값으로 취급됨
// 블록 형태 역시 지원되며 마지막 라인이 자연스럽게 return 적용
// 만약 Unit(무의미한 값)이어도 그냥 흘려보내면 끝

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
}