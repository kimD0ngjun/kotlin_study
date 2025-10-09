package coroutines.basic

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

/**
 * Kotlin에서 코루틴을 생성하려면 얘네들 필요
 * 1) 서스펜딩 함수
 * 2) 실행 가능한 코루틴 범위(예: withContext() 함수 내부)
 * 3) 시작을 위한 CoroutineScope.launch() 같은 코루틴 빌더
 * 4) 사용 스레드를 제어할 디스패처
 */
suspend fun process() {
    println("process() 스레드 네임: ${Thread.currentThread().name}")
    delay(1.seconds) // DB 연결이나 API 콜 데이터 조회 같은 시간 소요 작업 예시
    // Thread.sleep과 다르게 delay는 스레드를 점유하지 않고, 코루틴 전용 논 블로킹 슬립 메소드임
}

/**
 * 공식 문서 왈...
 * 일부 프로젝트에서는 main() 함수를 suspend로 표시할 수 있지만,
 * 기존 코드와 통합하거나 프레임워크를 사용할 때는 불가능할 수 있습니다.
 * 이 경우 프레임워크 문서에서 서스펜딩 함수 호출을 지원하는지 확인하세요.
 * 지원하지 않는다면 runBlocking()을 사용하여 현재 스레드를 차단하며 호출하십시오. -> 중요하네잉
 */
/**
 * 공유 스레드 풀에서 실행되는 멀티스레드 동시 코드의 진입점을 정의하려면 withContext(Dispatchers.Default)를 사용
 */
suspend fun main() {
    /**
     * 코루틴 실행 범위를 정하는 거임(2)
     * 이 블록은 이 스레드 풀에서 실행해라~ 같은 의미
     * Dispatchers.Default : 코루틴에서 CPU 바운드 작업(계산, 반복문, 알고리즘 등)에 최적화된 공유 스레드 풀
     */
    /**
     * withContext() 함수는 일반적으로 컨텍스트 전환에 사용되지만, 이 예제에서는 동시 실행 코드를 위한 비차단 진입점도 정의
     * withContext() 블록 내부에서 시작된 코루틴들은 동일한 코루틴 범위를 공유하면서 구조화된 동시성을 보장
     */
    withContext(Dispatchers.Default) { // this: CoroutineScope
        // Add the coroutine builders here
        // Start a coroutine inside the scope with CoroutineScope.launch()
        this.launch { process() }

        // 또다른 코루틴 지정
        this.launch {
            println("코루틴 런칭 스코프의 스레드: ${Thread.currentThread().name}")
            delay(1.seconds) // 역시나 API 콜, 네트워크 작업 같은 것들 예시
        }

        println("withContext() 스레드: ${Thread.currentThread().name}")
    }

    /**
     * 스레드 풀에는 CPU 코어 수만큼의 스레드가 기본적으로 생성
     * 코루틴이 launch될 때 풀에 있는 어떤 스레드든 자유롭게 선택
     * 그래서 같은 CoroutineScope에서 여러 코루틴을 실행해도, 각 코루틴이 다른 스레드에 배정될 수 있음
     * → 스레드 이름이 달라질 수 있다는데.. 글쎼... 난 아니던데
     */
}