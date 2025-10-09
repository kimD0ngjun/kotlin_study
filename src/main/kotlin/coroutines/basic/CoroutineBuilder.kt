package coroutines.basic

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

/**
 * 코루틴 빌더 함수는 실행할 코루틴을 정의하는 서스펜드 람다를 받아들이는 함수
 * 빌더 함수들은 실행될 코루틴 스코프가 요구됨
 * 기존 스코프든, coroutineScope(), runBlocking(), withContext()와 같은 헬퍼 함수를 사용하여 생성한 스코프든...
 */
suspend fun main() {
    performBackgroundWork()
    /**
     * 코루틴 스코프
     * 백그라운드 작업 실행
     */

    asyncWork()
    /**
     * 두 번째 페이지
     * 첫 번째 페이지
     * 같은 페이지일까요? : false
     */
}

/**
 * 1) CoroutineScope.launch()
 * CoroutineScope의 확장 함수
 * 기존 코루틴 스코프 내에서 나머지 범위를 차단하지 않고 새 코루틴 시작(결과를 기다리지 않고 병렬적 작업 처리)
 */
suspend fun performBackgroundWork() = coroutineScope {
    // this: CoroutineScope
    // 스코프를 블로킹하지 않고 코루틴 실행
    this.launch {
        delay(100.milliseconds) // 백그라운드 작업 실행 임시 딜레이
        println("백그라운드 작업 실행")
    }

    // 메인 코루틴은 백그라운드가 중단돼도 실행
    println("코루틴 스코프")
}

/**
 * 2) CoroutineScope.async()
 * CoroutineScope의 확장 함수
 * 기존 코루틴 스코프 내에서 동시 연산 시 -> 최종 결과를 Deferred 타입 객체로 반환(자바 Future의 현대적 확장)
 * 결과 준비를 위한 코드 일시중지는 await() 함수로
 */
suspend fun asyncWork() = withContext(Dispatchers.Default) {
    // this: CoroutineScope
    val firstPage: Deferred<String> = this.async {
        delay(100.milliseconds)
        println("첫 번째 페이지")
        "첫 번째 페이지"
    }

    val secondPage: Deferred<String> = this.async {
        delay(50.milliseconds)
        println("두 번째 페이지")
        "두 번째 페이지"
    }

    // 자바의 Future.get()처럼 await()도 결과를 갖고올 때까지 코드(블록 실행)를 멈춘다
    // 다만 스레드 블로킹을 하지 않고 그냥 코루틴만 중단(suspend)한다
    val pagesAreEqual: Boolean = firstPage.await() == secondPage.await()
    println("같은 페이지일까요? : $pagesAreEqual")
}