package coroutines.basic

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    launchJob()
    asyncDeferred()
}

/**
 * 앞서 봤듯, 코루틴은 그 자체로 실행되지 않고 CoroutineScope(컨테이너) 내부에서 새로운 코루틴을 생성하는 함수가 필요하다
 * 그게 코루틴 빌더이며, 대표적으로 launch()와 async()가 있다.
 */
suspend fun launchJob() = coroutineScope {
    // this: CoroutineScope

    // launch()는 결과값이 없는 코루틴을 실행한다.
    val job: Job = this.launch {
        delay(1000)
        val sum = (1..10).sum()
        println("합계: $sum")
        sum // 어차피 결과값 없는 코루틴 실행이니 얘는 무시됨
    }
    /**
     * 결과값이 없음에도 Job 객체에 담을 수 있는 이유는,
     * 얘는 결과값 담기 용도가 아니라 코루틴의 생명주기를 제어하기 위한 용도다
      */
    println("job 상태 : ${job.isActive}")
    job.cancel() // 코루틴 실행 제어 -> 작업 취소!
    println("1초 지난 후 job 상태 : ${job.isActive} // 취소 여부 : ${job.isCancelled}")
}

/**
 * await을 호출하면 해당 코루틴만 중단되고, 나머지는 그대로 동작한다
 * 결과를 나중에 받아야 하지만, 기다리는 동안은 CPU를 놀리지 않게 한다
 *
 * -> 이게 future.get()과의 차이
 */
suspend fun asyncDeferred() = coroutineScope {
    val sum1: Deferred<Int> = this.async { (1..100).sum() }
    val sum2: Deferred<Int> = this.async { (101..200).sum() }

    // Deferred 덕분에 await()으로 결과값 조합 가능
    val total = sum1.await() + sum2.await()
    println("총합: $total")
}