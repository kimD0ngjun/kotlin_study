package grammer

fun main() {
    var sum = 0

    // number는 iterator 변수
    for (number in 1..5) {
        sum += number
    }

    println(sum)
    sum = 0

    val iter = (1..5).iterator()
    while (iter.hasNext()) {
        val number = iter.nextInt()
        sum += number
    }
    println(sum)

    // 위와 아래는 완벽히 매칭. 똑같은 구조임(number 변수가 iterator 변수)
    // 컬렉션, Array, Range 등 iterator()가 구현된 모든 타입에서 활용 가능

    val cakes: List<String> = listOf("strawberry cake", "chocolate cake", "cheese cake")
    for (cake in cakes) {
        print("${cake}도 먹고... ")
    }

    val iter2 = cakes.iterator()
    while(iter2.hasNext()) {
        val cake = iter2.next()
        print("${cake}도 먹고... ")
    }

    println()

    var a = 0
    var b = 2

    do {
        a += 1
        println("a plus: $a")
    } while (a < b)
}