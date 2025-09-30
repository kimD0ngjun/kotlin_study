package intermediate.null_safety

// https://kotlinlang.org/docs/kotlin-tour-intermediate-null-safety.html#early-returns-and-the-elvis-operator
/**
 * 엘비스 연산자는 꽤나 유용하다
 * null이 튀어나오면 다른 값으로 대체하는 것을 넘어 아예 해당 지정 값으로 리턴시켜버릴 수도 있다
 */

data class Person(
    val id: Int,
    val name: String,
    val friends: List<Int>
)

fun getNumbersOfFriends(people: Map<Int, Person>, personId: Int): Int {
    // person id로 못 찾으면 바로 리턴하면 되지 후속 조치할 필요 x
    val person = people[personId] ?: return -1 // 엘비스 연산자 + 조기 리턴
    return person.friends.size
}

fun main() {
    val person1 = Person(1, "Alice", listOf(2, 3))
    val person2 = Person(2, "Bob", listOf(1))
    val person3 = Person(3, "Charlie", listOf(1))

    val people = mapOf<Int, Person>(
        1 to person1,
        2 to person2,
        3 to person3
    )

    println(getNumbersOfFriends(people, 1))
    println(getNumbersOfFriends(people, 3))
    println(getNumbersOfFriends(people, 4)) // -1
}