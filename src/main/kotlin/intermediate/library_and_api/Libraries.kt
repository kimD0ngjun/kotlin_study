package intermediate.library_and_api

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.datetime.*

/**
 * 코틀린 2.2.2 최신 버전에서 이미 코틀린 자체 시간 함수를 제공하는데 얘는 실험적인 상황이고,
 * 코틀린엑스 데이트타임 라이브러리는 최신 버전에서 이미 Clock이 deprecated된 상황
 */
@OptIn(ExperimentalTime::class) // 나는 이 실험적 API를 사용하는 걸 알고 있다..라고 컴파일러에게 알려주는 표시
fun main() {
    val nowByKotlinTime = Clock.System.now()
    println(nowByKotlinTime)
}