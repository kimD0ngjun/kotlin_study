package intermediate.scopeFunction

// https://kotlinlang.org/docs/kotlin-tour-intermediate-scope-functions.html#with

/**
 * with은 다른 범위 함수와 다르게 확장 함수가 아니라 수신 객체 자체를 파라미터로 받음
 * 객체에 대한 여러 함수를 호출하려면 with 범위 함수 활용해보기
 */

class Canvas {
    fun rect(x: Int, y: Int, w: Int, h: Int): Unit = println("$x, $y, $w, $h")
    fun circ(x: Int, y: Int, rad: Int): Unit = println("$x, $y, $rad")
    fun text(x: Int, y: Int, str: String): Unit = println("$x, $y, $str")
}

fun main() {
    val canvas = Canvas()

    // 상당히 읽기 지저분...
    canvas.text(10, 10, "Foo")
    canvas.rect(20, 30, 100, 50)
    canvas.circ(40, 60, 25)
    canvas.text(15, 45, "Hello")
    canvas.rect(70, 80, 150, 100)
    canvas.circ(90, 110, 40)
    canvas.text(35, 55, "World")
    canvas.rect(120, 140, 200, 75)
    canvas.circ(160, 180, 55)
    canvas.text(50, 70, "Kotlin")

    // 객체의 여러 멤버를 참조하면서도 깔끔하게 묶을 수 있기도 하다
    with(canvas)  {
        text(10, 10, "Foo")
        rect(20, 30, 100, 50)
        circ(40, 60, 25)
        text(15, 45, "Hello")
        rect(70, 80, 150, 100)
        circ(90, 110, 40)
        text(35, 55, "World")
        rect(120, 140, 200, 75)
        circ(160, 180, 55)
        text(50, 70, "Kotlin")
    }
}