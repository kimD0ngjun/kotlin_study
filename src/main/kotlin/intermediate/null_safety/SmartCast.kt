package intermediate.null_safety

// https://kotlinlang.org/docs/kotlin-tour-intermediate-null-safety.html#is-and-is-operators
/**
 * is는 해당 값이 특정 타입인지 확인한다
 * !is는 그 반대
 * 즉, 스마트 캐스팅
 */

fun check(obj: Any?) {
    when (obj) {
        is Int -> println("이것은 Int")
        is Double -> println("이것은 Double")
        !is String -> println("이것은 문자열이 아님")
        else -> println("잘 모르겠당")
    }
}

fun main() {
    check(10)
    check(10.0)
    check(listOf("string"))
    check("안녕")
}