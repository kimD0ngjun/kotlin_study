package intermediate.scopeFunction

// https://kotlinlang.org/docs/kotlin-tour-intermediate-scope-functions.html#also

fun main() {
    val medals: List<String> = listOf("Gold", "Silver", "Bronze")
    val result: List<String> = medals
            .map { it.uppercase() } // 가끔 이런 중간중간 과정들의 로그를
            .filter { it.length > 4 } // 확인하고 싶어지는 경우가 생길 수 있다
            .reversed()
    println(result)

    val resultWithAlso: List<String> =
        medals
            .map { it.uppercase() }
            .also { println(it) }
            // [GOLD, SILVER, BRONZE]
            .filter { it.length > 4 }
            .also { println(it) }
            // [SILVER, BRONZE]
            .reversed()
    println(resultWithAlso)
}

/**
 * also 확장함수 취지 자체가 사이드 이펙트용으로 설계돼서 로깅이나 디버깅에 매우 최적
 * 로깅을 남기는 것도 사이드 이펙트 중 하나
 */