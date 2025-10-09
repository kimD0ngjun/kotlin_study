package coroutines.basic

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 컨텍스트 : 코루틴 실행 환경 정보 집합
 * 디스패처 : 컨텍스트의 구성요소 중 코루틴이 실행될 스레드 혹은 스레드풀 결정 및 관리
 *
 * 코루틴 디스패처는 코루틴이 실행에 사용할 스레드 또는 스레드풀을 제어 관리함
 * 코루틴이 특정 스레드에 종속되지 않고, 중단 후에 다른 스레드에서 재개될 수도 있어서 각 코루틴마다 별도의 스레드 할당 불필요 + 동시 실행 가능
 *
 * 코루틴은 서로 다른 스레드에서 일시 중지 및 재개될 수 있지만,
 * 코루틴이 일시 중지되기 전에 작성된 값은 재개 시 동일한 코루틴 내에서 여전히 사용할 수 있도록 보장
 */
/**
 * 코루틴 스코프 : 코루틴 생명주기 제어
 * 디스패처 : 코루틴 실행에 사용되는 스레드 제어
 *
 * 모든 코루틴에 대해 디스패처를 명시적으로 지정할 필요는 x
 * 기본적으로 코루틴은 상위 범위의 디스패처를 상속받으며 다른 컨텍스트에서 코루틴을 실행하려면 디스패처를 지정할 수 있음
 * 만약 코루틴 컨텍스트에 디스패처가 포함되어 있지 않은 경우, 코루틴 빌더는 Dispatchers.Default를 사용
 */

suspend fun main() {
    runWithDispatcher()
    testWithContext()
}

// 코루틴 빌더에 디스패처를 지정하려면 원하는 디스패처를 파라미터로 전달하면 된다
suspend fun runWithDispatcher() = coroutineScope {
    // this: CoroutineScope
    this.launch(Dispatchers.Default) {
        println("러닝 온 ${Thread.currentThread().name}")
    }
}

// 대체재로 withContext도 있음
// 디스패처의 관리 영역을 명확하게 분리 + 관리 할 수 있음
// 블록 종료 시, 호출한 코루틴으로 컨텍스트가 다시 복귀
suspend fun testWithContext() = withContext(Dispatchers.Default) {
    // this: CoroutineScope
    println("withContext 블록에서 실행 : ${Thread.currentThread().name}")

    val one: Deferred<Long> = this.async {
        println("첫 번째 연산 실행 : ${Thread.currentThread().name}")
        val sum = (1L..500_000L).sum()
        delay(200L)
        println("첫 번째 연산 완료 : ${Thread.currentThread().name}")
        sum
    }

    val two: Deferred<Long> = this.async {
        println("두 번째 연산 실행 : ${Thread.currentThread().name}")
        val sum = (500_000L..1_000_000L).sum()
        println("두 번째 연산 완료 : ${Thread.currentThread().name}")
        sum
    }

    // 둘 다 실행될 때까지 대기
    println("두 연산의 합산 결과 : ${one.await() + two.await()}")
}

// Dispatchers.Default(CPU 연산 중심) 외에도 Dispatchers.IO(입출력 연산 중심), Dispatchers.Main(UI 스레드 중심) 등등..