package coroutines.notion

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

suspend fun printPeriods() = coroutineScope {
    repeat(50_000) {
        launch {
            delay(5.seconds)
        }
    }
}

fun main() = runBlocking {
//    // 코루틴 측
//    val coroutineTime = measureTime {
//        printPeriods()
//    }
//    println("\n코루틴 소요 시간: $coroutineTime") // 코루틴 소요 시간: 5.316244583s
//
//    // 스레드 측 (개수 줄여서 안정적으로 테스트)
//    val threads = mutableListOf<Thread>()
//    val threadCount = 500 // 50_000개는 위험
//    val threadTime = measureTime {
//        repeat(threadCount) {
//            val t = thread {
//                Thread.sleep(5000L)
//                print(".")
//            }
//            threads += t
//        }
//        threads.forEach { it.join() } // 모든 스레드 종료 대기
//    }
//    println("\n스레드($threadCount) 소요 시간: $threadTime") // 스레드(500) 소요 시간: 5.027045417s

    println("=== 코루틴 테스트 ===")
    val timeCoroutines = measureTime {
        printPeriods()
    }
    println("코루틴 소요 시간 : $timeCoroutines\n")

    println("=== 전통 스레드 테스트 (500개) ===")
    val threads = mutableListOf<Thread>()
    val timeThreads = measureTime {
        repeat(500) {
            val t = thread {
                Thread.sleep(5000L)
            }
            threads += t
        }
        threads.forEach { it.join() }
    }
    println("스레드(500) 소요 시간 : $timeThreads\n")

    println("=== 가상 스레드 테스트 (50_000개) ===")
    val virtualThreads = mutableListOf<Thread>()
    val timeVirtual = measureTime {
        repeat(50_000) {
            val t = Thread.startVirtualThread {
                Thread.sleep(5000L)
            }
            virtualThreads += t
        }
        virtualThreads.forEach { it.join() }
    }
    println("가상 스레드(50_000) 소요 시간 : $timeVirtual\n")

    /**
     * === 코루틴 테스트 ===
     * 코루틴 소요 시간 : 5.182111541s
     *
     * === 전통 스레드 테스트 (500개) ===
     * 스레드(500) 소요 시간 : 5.029273334s
     *
     * === 가상 스레드 테스트 (50_000개) ===
     * 가상 스레드(50_000) 소요 시간 : 5.413198833s
     */
}
