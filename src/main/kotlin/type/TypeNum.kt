fun main() {
    println(4/2)
    println(4.0/2)
    println(4.0/2.0)

    val a = 4 / 2
    val b = 4.0 / 2
    val c = 4.0 / 2.0

    println(a::class) // class kotlin.Int
    println(b::class) // class kotlin.Double
    println(c::class) // class kotlin.Double

    println(5.plus(4))
    println(3.div(2))
    println(7.minus(9))
    println(4.times(3)) // 곱하기
    println(13.mod(2)) // 나머지
}