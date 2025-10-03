package corutines.basic

import kotlinx.coroutines.*

/**
 * Kotlin에서 코루틴을 생성하려면 얘네들 필요
 * 1) 서스펜딩 함수
 * 2) 실행 가능한 코루틴 범위(예: withContext() 함수 내부)
 * 3) 시작을 위한 CoroutineScope.launch() 같은 코루틴 빌더
 * 4) 사용 스레드를 제어할 디스패처
 */
suspend fun process() {
    println("현재 스레드 네임: ${Thread.currentThread().name}")
    delay(1_000L) // DB 연결이나 데이터 조회 같은 시간 소요 작업 예시
}

/**
 * 공식 문서 왈...
 * 일부 프로젝트에서는 main() 함수를 suspend로 표시할 수 있지만,
 * 기존 코드와 통합하거나 프레임워크를 사용할 때는 불가능할 수 있습니다.
 * 이 경우 프레임워크 문서에서 서스펜딩 함수 호출을 지원하는지 확인하세요.
 * 지원하지 않는다면 runBlocking()을 사용하여 현재 스레드를 차단하며 호출하십시오. -> 중요하네잉
 */
suspend fun main() {}