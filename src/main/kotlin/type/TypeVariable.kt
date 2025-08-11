package type

fun main() {
    val i: Int = 15
    println(i::class)

    val b = i.toByte()
    println(b) // Byte 타입 변환

    val b16 = i.toByte().toString(16) // 파라미터: 진수
    println(b16) // f

    val c = i.toDouble()
    println(c)
}