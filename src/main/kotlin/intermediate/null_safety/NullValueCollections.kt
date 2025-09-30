package intermediate.null_safety

// https://kotlinlang.org/docs/kotlin-tour-intermediate-null-safety.html#null-values-and-collections
/**
 * 코틀린의 null 안전과 연관된 컬렉션 메소드들이 존재함
 */
fun main() {
    val emails: List<String?> = listOf("test1@test.com", null, "test2@test.com", null, "test3@test.com")
    val validEmails = emails.filterNotNull() // 딱 봐도 null 아닌 애들만 거르는...
    println(validEmails) // [test1@test.com, test2@test.com, test3@test.com]

    val serverConfigs: Map<String, String> = mapOf(
        "appConfig.json" to "App Configuration",
        "dbConfig.json" to "Database Configuration"
    )
    val requestedFile = "appConfig.json"
    val configFiles = listOfNotNull(serverConfigs[requestedFile]) // 딱 봐도 null 아닌 애들 모아서 리스트 만드는...
    println(configFiles) // [App Configuration]

    /**
     * 자바에서 빈 리스트에서 최대나 최소값을 찾으려고 하면 예외를 던짐(NoSuchElementException)
     * 코틀린은 없음을 표현하기 위해 null을 반환시키는 거
     */
    val existedList: List<Int> = listOf(1, 2, 3)
    val nullList: List<Int> = listOf()
    println(existedList.maxOrNull()) // 3
    println(existedList.minOrNull()) // 1
    println(nullList.maxOrNull()) // null
    println(nullList.minOrNull()) // null

    /**
     * signleOrNull{} : 람다를 파라미터로 받아서 조건에 부합하는 요소가 1개면 해당 요소 반환
     * 조건에 부합하는 게 복수 개거나 없으면 null 반환
     */
    val exampleList: List<Int> = listOf(1, 2, 3, 4)
    println(exampleList.singleOrNull { it == 4 }) // 4
    println(exampleList.singleOrNull { it == 5 }) // null becuase of none
    println(exampleList.singleOrNull { it % 2 == 0 }) // null becuase of no single(2, 4)

    val users: List<User> = listOf(
        User(null, 25),
        User("K", null),
        User("B", 30)
    )

    // 하나하나 탐색하다면서 null인 건 지나치고 null이 아닌 놈 처음 마주하면 걔 반환
    // 만약 끝까지 다 돌았는데도 null만 나오면 null 반환
    println(users.firstNotNullOfOrNull { it.name }) // K

    /**
     * 누적값 생성 고차함수는 reduce
     * 근데 빈 컬렉션이면 당연히 null
     */
    val itemPrices = listOf(20, 35, 15, 40, 10)
    val totalPrice = itemPrices.reduceOrNull { acc, i -> acc + i  } // 누적값, 요소 -> 누적값 + 요소
    println(totalPrice) // 120
    val emptyList = listOf<Int>()
    val totalEmptyList = emptyList.reduceOrNull { acc, i -> acc + i  }
    println(totalEmptyList) // null
}

data class User(val name: String?, val age: Int?)