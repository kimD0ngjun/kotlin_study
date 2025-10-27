package coroutines.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
//    notion()
    spreadCancel()
}

/** *
 * runBlocking이 끝나기 전에 내부의 모든 자식 코루틴이 완료될 때까지 자동으로 기다려준다
 * == 부모 스코프가 자식들의 생명주기를 관리한다
 */
suspend fun notion() = coroutineScope {
    // 첫째 자식
    launch {
        delay(1000)
        println("자식 1 완료")
    }

    // 둘째 자식
    launch {
        delay(2000)
        println("자식 2 완료")
    }

    println("부모 코루틴 종료 대기중...")
}

/**
 * 부모 코루틴 종료 대기 중...
 * 자식 1 완료
 * 자식 2 완료 -> 여기까지도 부모 코루틴 살아있는 거
 */

/**
 * 취소 전파
 * - 기본적으로 부모 -> 자식 방향으로 취소가 전파됨
 */
fun spreadCancel() = runBlocking {
    val parent = this.launch {
        val child = this.launch {
            try {
                repeat(10) {
                    println("자식 실행중...$it")
                    delay(100)
                }
            } finally {
                println("자식 취소")
            }
        }

        delay(250)
        println("부모에서 자식 취소")
        child.cancel() // 자식만 취소
    }

    parent.join()
    println("부모 코루틴 종료: ${parent.isCompleted}")
}