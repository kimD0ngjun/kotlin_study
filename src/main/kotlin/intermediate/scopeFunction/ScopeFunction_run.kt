package intermediate.scopeFunction

// https://kotlinlang.org/docs/kotlin-tour-intermediate-scope-functions.html#run

/**
 * 앞서 객체 초기화 시점을 조율하기 위해 apply 스코프 함수를 썼는데,
 * 만약 객체 초기화와 연산 과정을 명확히 분리하고 싶으면 run 스코프 함수도 같이 써보자
 */
// 참고로 코틀린 탑레벨 변수는 패키지 내에서 전역적 싱글톤 상태... 그러므로 변수명 같게 쓰면 안됑...
val client_apply: Client = Client().apply {
    token = "asdf" // psvm 내에서 초기화를 위해 필드 등을 참조할 필요 없게 분리
}

fun main() {
    // 객체 초기화와 실행을 분리
    var result: String = client_apply.run {
        // psvm 스코프에서 참조 x, 람다 함수로 전달
        connect()
        authenticate()
        getData()
    }

    println(result)
}