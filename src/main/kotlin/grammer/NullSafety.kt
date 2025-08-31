package grammer

// https://kotlinlang.org/docs/kotlin-tour-null-safety.html
/**
 * 코틀린에서 null 처리
 *
 * 프로그램에서 null 값이 허용되는 경우를 명시적으로 선언
 * null 값을 확인
 * null 값을 포함할 수 있는 속성이나 함수에 대한 안전 호출을 사용
 * null 값이 감지될 경우 취할 조치를 선언
 */

fun main() {
    // neverNull has String type
    var neverNull: String = "This can't be null"
    // Throws a compiler error
//    neverNull = null

    // nullable has nullable String type
    var nullable: String? = "You can keep a null here"
    // This is OK
    nullable = null

    // By default, null values aren't accepted
    var inferredNonNull = "The compiler assumes non-nullable"
    // Throws a compiler error
//    inferredNonNull = null

    // notNull doesn't accept null values
    fun strLength(notNull: String): Int {
        return notNull.length
    }

    println(strLength(neverNull)) // 18
//    println(strLength(nullable))  // Throws a compiler error

    // 엘비스 연산자(null이 나올 경우를 대비한 디폴트값 할당)
    var nullableString: String? = null
    // nullableString?.length ?: 0
    // nullableString이 null이거나, nullableString은 null이 아니지만 .length가 null이거나
    println("널 가능한 문자열의 길이: ${nullableString?.length ?: 0}")

    val city = City("Seoul")
    println("도시 정보 : ${city?.name ?: "도시 어딘가"}")
}

data class City(val name: String)