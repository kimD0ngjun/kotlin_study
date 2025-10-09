package coroutines.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

/**
 * 앱에서 다수의 코루틴을 실행할 때의 그룹 단위 관리하는 방법 -> 구조화된 동시상
 * 다른 말이 아니라 트리 구조로 부모-자식 코루틴 간의 라이프사이클 형성
 *
 * 부모 코루틴은 자식 코루틴이 종료할 때까지 기다리고 종료하며, 부모 코루틴이 실패하거나 취소하면 모든 자식 코루틴도 재귀적 취소
 * 새로운 코루틴의 생명주기는 오직 CoroutineScope 내에서만 시작 가능
 * 이 CoroutineScope에 디스패처 및 기타 실행 속성 정의하는 코루틴 컨텍스트가 포함됨
 * 특정 코루틴 내에서 다른 코루틴을 시작하면, 그 코루틴은 부모 코루틴이 됨
 * -> CoroutineScope 내에서 CoroutineScope.launch() 같은 코루틴 빌더 함수를 호출하면 부모-자식 코루틴 형성 및 자식 코루틴 생명주기 시작
 */

/**
 * 컨텍스트 : 코루틴 실행 환경 정보 집합
 * 디스패처 : 컨텍스트의 구성요소 중 코루틴이 실행될 스레드 혹은 스레드풀 결정 및 관리
 */

// 현재 코루틴 컨텍스트에서 새로운 코루틴을 생성하려면 coroutineScope() 함수를 사용 -> 코루틴 서브트리의 루트 코루틴 생성
/**
 * 헷갈릴 수 있는데, 부모 자식 관계는 coroutineScope '블록' 기준임
 * launch 함수는 항상 비동기적으로 실행, 얘는 부모 자식 관계와는 무관함
 */
suspend fun main() {
    // 코루틴 서브트리의 루트(자식들의 시작)
    // coroutineScope()는 블록 안의 자식 코루틴들이 종료할 때까지 기다리는(suspending) 함수
    // 코루틴 규칙에 의해 suspend 함수는 suspend 함수 안에서만 호출 가능 -> 그래서 psvm에 suspend 키워드 추가
    coroutineScope {
        // 디스패처 명시 없으므로 현재 컨텍스트 상속
        this.launch {
            // launch 함수는 항상 비동기적으로 실행(부모 자식과 무관)
            this.launch {
                delay(2.seconds)
                println("손자 코루틴 완료")
            }
            println("자식1 코루틴 완료")
        }

        this.launch {
            delay(1.seconds)
            println("자식2 코루틴 완료")
        }

        launchChild()
    }

    // 코루틴 스코프 블록의 모든 자식들이 종료되고 나서 실행
    println("코루틴 스코프 종료")
    /**
     * 자식1 코루틴 완료
     * 자식2 코루틴 완료
     * 손자 코루틴 완료
     * 코루틴 스코프 종료
     */
}

// 확장함수 기반 코루틴 스코프 블록 내 자식 코루틴 정의
fun CoroutineScope.launchChild() {
    this.launch { println("확장 함수에서 코루틴 완료1") }
    this.launch { println("확장 함수에서 코루틴 완료2") }
}