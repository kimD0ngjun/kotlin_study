package coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    notion()
//    defaultCancel()
//    upwardCancel()
//    launchException()
//    asyncException()
    globalScopeException()
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
fun defaultCancel() = runBlocking {
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

/**
 * 예외 전파
 * - 기본적으로 자식 -> 부모 방향으로 예외가 전파됨(상향식 예외 전파)
 * 근데 launch랑 async가 조금 취급이 다름
 */
fun launchException() = runBlocking {
    val parent = launch {
        val child = launch {
            println("자식 시작")
            delay(500)
            throw RuntimeException("자식 예외 발생!")
        }

//        try {
//            child.join() // 예외는 join 시점, 즉 코루틴 종료 시에 부모로 재전달됨
//        } catch (e: Exception) {
//            println("부모가 자식 예외 감지: $e")
//        }

        // try catch로 자식의 예외 가능성을 잘 제어하지 않으면 부모까지 다 전파돼서 부모 코루틴 스코프도 강제 종료된다
        repeat(10) {
            delay(200)
            println("부모 $it 번째 작업 수행 중...")
        }
    }

    parent.join()
    println("전체 코루틴 스코프 종료")
}

// async는 어쨌든 결과를 반환함. 즉, Deferred 객체에 결과를 담는데, 그 결과가 예외가 된 것일 뿐
// 그래서 await() 시점에 예외가 부모 스코프에 전파된다.
fun asyncException() = runBlocking {
    val deferred = async {
        println("자식 async 시작")
        throw RuntimeException("비동기 예외")
    }

    /**
     * 하지만 구조적 동시성에서 부모 스코프에 속한 async는 예외 발생 즉시 부모 Job을 취소
     * 그래서 이 밑까지 도달할 수 없는 것임
     */

    // 아직 예외 안 남
    repeat(5) {
        delay(100)
        println("부모 $it 번쨰 작업 : 자식 await 전엔 아무 일 없음")
    }

    try {
        deferred.await() // 이때 예외가 던져지는 게 이론적으로 맞음 하지만...
    } catch (e: Exception) {
        println("await에서 예외 감지: $e")
    }
}

/**
 * 근데 GlobalScope는 좀 희한함(구조화된 동시성을 포기한 형태)
 */
fun globalScopeException() = runBlocking {
    // 자식과 부모의 관계를 완전히 끊어버려서 구조화된 동시성이 아님
    // 그래서 자식에서 예외가 터져도 부모가 휩쓸리지 않는다
    val child = GlobalScope.launch {
        println("자식(GlobalScope) 시작")
        throw RuntimeException("자식 예외")
    }

    repeat(5) {
        delay(100)
        println("부모 반복 : $it")
    }

    println("부모 반복 끝")
    delay(500) // 자식이 끝날 때까지 잠시 대기
}

/**
 * 구조적 동시성(structured concurrency):
 * 부모-자식 관계로 Job을 묶음 →
 * 부모가 끝나면 자식도 끝,
 * 예외도 부모에게 전파 → 전체 스코프 안전 관리 가능
 *
 * GlobalScope:
 * 부모-자식 관계 없음 → 코루틴이 어디서 시작됐는지 트래킹 불가(메모리 누수... 예외 트래킹 불가 등등..)
 * 부모 종료와 상관없이 자식은 살아있음 → 생명주기 관리가 전적으로 개발자 책임
 */