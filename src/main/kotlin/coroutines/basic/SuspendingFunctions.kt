package coroutines.basic

// https://kotlinlang.org/docs/coroutines-basics.html#suspending-functions
/**
 * 서스펜딩 함수: 실행 중인 작업을 일시 중지하고 나중에 재개
 * 코드 구조에 영향 x
 */
suspend fun greet(): Unit = println("안녕, 현재 실행되는 스레드는 ${Thread.currentThread().name}")

/**
 * 서스펜딩 함수는 다른 서스펜딩 함수에서만 호출 가능
 * 코틀린 앱 진입점에서 서스펜딩 함수 호출하려면 심지어 main()에도 서스펜드 키워드 지정 가능
 */
suspend fun introduce(): Unit {
    println("소개 준비 중...")
    greet()
    println("인사 완료!")
}

suspend fun main() {
    introduce()
}

/**
 * 이 예제는 아직 동시성을 사용하지 않지만, 함수에 suspend 키워드를 지정함으로써
 * 다른 중단 가능한 함수를 호출하고 내부에서 동시 코드를 실행 가능
 *
 * suspend 키워드는 코틀린 자체 지정 키워드지만, 코루틴 대부분 기능은 kotlinx.coroutines 라이브러리를 통해 사용
 */
