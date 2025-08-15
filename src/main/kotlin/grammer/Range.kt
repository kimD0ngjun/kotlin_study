package grammer

fun main() {
    // 반복문을 위한 Range 문법
    // .. == 이상, 이하
    for (i in 1..4) {
        println(i)
    }

    // ..< == 이상, 미만(상한)
    for (i in 1..<4) {
        println(i)
    }

    // downTo == 이하, 이상(역순)
    for (i in 4 downTo 1) {
        println(i)
    }

    // step : 간격
    for (i in 1..10 step 2) {
        println(i) // 1 3 5 7 9
    }

    // 문자도 가능
    for (c in 'a'..'z' step 2) {
        print(c)
    }
}