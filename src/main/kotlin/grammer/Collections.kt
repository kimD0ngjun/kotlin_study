package grammer

fun main() {
    /*
    List
     */

    var readOnlyShape = listOf("tri", "rect", "cir")
    println(readOnlyShape) // 자바의 읽기 전용 불변 리스트 메소드랑 똑같이 리스트 요소들 바로 출력되네
//    readOnlyShape[0] = "new_tri"

    var shapes: MutableList<String> = mutableListOf("tri", "rect", "cir")
    shapes[0] = "new_tri"
    println(shapes)

    var noMutableList: List<String> = shapes
//    noMutableList[0] = "new_new_tri"
    // 다시 불변 컬렉션 회귀...
    println(noMutableList)

    // 문자열 템플릿도 가능
    println("the first shape is ${noMutableList[0]}")
    println("the first shape is ${noMutableList.first()}")
//    println("the last shape is ${noMutableList[-1]}")
    // 아쉽게도 파이썬처럼 구현되진 않았네잉... 코틀린의 컬렉션 사이즈는 .size
    println("the last shape is ${noMutableList[noMutableList.size - 1]}")
    println("the last shape is ${noMutableList.last()}")

    // 카운팅, 존재 여부 확인도 가능
    println("the count is ${noMutableList.count()}")
    println("cir" in noMutableList)
    println("penta" in noMutableList)

    // 요소 추가 삭제도 가능
    shapes.add("penta")
    println("shapes: $shapes")
    shapes.remove("penta")
    println("shapes: $shapes")

    /*
    Set
     */
    val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")
//    readOnlyFruit.add("peach") // 당연히 추가 불가
    println(readOnlyFruit)

    val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")
    fruit.add("peach")
    val noMutableFruits: Set<String> = fruit
//    noMutableFruits.add("peach") // 당연히 추가 불가
    println(fruit.remove("something")) // false 나올 거
    println(fruit.size)
    println("apple" in fruit)

    /*
    Map
     */
    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    // key to value... 오...
    println(readOnlyJuiceMenu)

    val menu = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    menu["meat"] = 30000
    println(menu)
    println(menu.remove("meat")) // 삭제된 key의 value를 반환
    println(menu.remove("meat")) // null 반환

    val noMutableMenu: Map<String, Int> = menu
//    noMutableMenu.remove("some menu")
//    noMutableMenu["some menu"] = 200000
    println(noMutableMenu)

    // key 존재여부
    println("apple" in menu)
    println("no menu" in menu.keys)
    println(menu.containsKey("apple"))

    // value 존재여부
    println(200 in menu.values)
    println(menu.containsValue(190))
}