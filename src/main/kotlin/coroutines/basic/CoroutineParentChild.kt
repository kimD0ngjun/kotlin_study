package coroutines.basic

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    notion()
//    downwardCancel()
    upwardCancel()
}

/** *
 * runBlocking이 끝나기 전에 내부의 모든 자식 코루틴이 완료될 때까지 자동으로 기다려준다
 * == 부모 스코프가 자식들의 생명주기를 관리한다
 */
fun notion() = runBlocking {
    val parent = launch {
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

    parent.join()
    println("모든 코루틴 종료")
}
/**
 * 부모 코루틴 종료 대기 중...
 * 자식 1 완료
 * 자식 2 완료 -> 여기까지도 부모 코루틴 살아있는 거
 */

/**
 * 취소 전파
 * - 기본적으로 부모 -> 자식 방향으로 취소가 전파됨(하향식 취소 전파)
 */
fun downwardCancel() = runBlocking {
    val parent = launch {
        val child = launch {
            try {
                repeat(10) {
                    println("자식 실행중...$it")
                    delay(100)
                }
            } finally {
                println("자식 종료됨")
            }
        }

        delay(250)
        println("부모에서 자식 취소 실행")
        child.cancel() // <- 직접 자식만 취소
        // 혹은 cancel() 대신 parent.cancel() 하면 전체 취소도 가능
    }

    parent.join()
    println("모든 코루틴 종료")
}

// 상향식, 즉 자식의 취소는 부모에게 영향을 주지 않는다.
// 단, 취소 여부를 부모에서 알 수는 있을듯?
fun upwardCancel() = runBlocking {
    val parent = launch {
        val child = launch {
            repeat(10) {
                delay(200)
                if (it == 3) {
                    println("자식이 스스로 취소")
                    this.cancel() // 자식만 취소
                }
                println("자식 실행중...$it")
            }
        }

        repeat(5) {
            delay(500)
            println("부모 실행중...$it")
        }
        println("자식 취소 여부 부모에서 확인: ${child.isCancelled}")
        println("부모 완료")
    }

    parent.join()
    println("모든 코루틴 스코프 종료")
}