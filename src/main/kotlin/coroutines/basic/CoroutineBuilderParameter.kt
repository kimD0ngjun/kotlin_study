package coroutines.basic

import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    perform()
}

/**
 * 앞서 봤듯, 코루틴은 그 자체로 실행되지 않고 CoroutineScope(컨테이너) 내부에서 새로운 코루틴을 생성하는 함수가 필요하다
 * 그게 코루틴 빌더이며, 대표적으로 launch()와 async()가 있다.
 */
suspend fun perform() = coroutineScope {
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


    // async()는 결과값이 있는 코루틴을 실행한다.
    this.async {

    }
}