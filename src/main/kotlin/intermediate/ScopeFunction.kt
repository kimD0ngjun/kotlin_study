package intermediate

// https://kotlinlang.org/docs/kotlin-tour-intermediate-scope-functions.html#scope-functions
/**
 * 프로그래밍 언어의 스코프는 전역과 지역으로 나뉨
 * 코틀린은 객체 주변에 임시 범위를 만들어 일부 코드를 실행할 수 있는 '스코프 함수'가 존재
 *
 * 이 스코프 함수의 핵심은, 언어와 라이브러리의 분리 취지가 담겨져 있음
 * 자바는 언어 레벨에서 많은 기능이 구현되고 추가됨 -> 새로운 기능에 따라 문법 변경이 잦음
 * 코틀린은 C처럼 stdlib와 언어를 분리하고자 함 -> 문법을 유지하면서 확장 함수와 범위 함수 등으로 언어 문법을 얇게 유지
 *
 * Kotlin에는 let, apply, run, also 및 with의 총 5가지 범위 함수가 있음
 * 각 범위 함수는 람다식을 받아 객체 또는 람다의 결과를 반환
 */

fun sendNotification(recipientAddress: String): String {
    println("Yo $recipientAddress!")
    return "Notification sent!"
}

fun getNextAddress(): String {
    return "sebastian@jetbrains.com"
}

fun main() {
    val address: String? = getNextAddress()
    // 컴파일 에러 Type mismatch: inferred type is String? but String was expected
    // null 가능 String과 그냥 String은 다르다
//    sendNotification(address)

    // 이를 극복할 수 있는 가장 간단한 건 엘비스 연산자 혹은 if-else 문법
    val elvis_address: String = getNextAddress() ?: "null"
    val elvis_confirm = sendNotification(elvis_address)

    /**
     * let은 널 안전, 값 가공을 위해 쓴다
     */

    // 더 간단한 방법은 'let' 범위함수를 사용하는 것
    // let은 어떤 값을 받아서 블록 안에서 잠깐 다른 이름(it)으로 쓰게 해주고, 블록의 결과를 돌려준다
    // ?. : null이 아니면 뒤의 놈 실행
    var confirm = address?.let {
        // it은 null 아닌 String으로 자동 변환 처리
        sendNotification(it)
        // let 블록의 마지막 표현식 결과가 let 전체의 반환값
    }

    confirm = address?.let {
        // 람다식을 넣으면 it 대신 다른 명칭 지정 가능
        value -> sendNotification(value)
    }
}