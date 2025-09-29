package intermediate.properties

import kotlin.properties.Delegates.observable // 명시적인 import 필요

/**
 * 관찰 가능한 프로퍼티
 * 얘도 지연 프로퍼티처럼 코틀린 stdlib에서 제공하는 거임
 * 보통 속성 값의 변화를 모니터링하기 위한 용도
 */

class Thermostat {
    /**
     * obersavable은 프로퍼티 초기화가 필요
     * property 자기자신, 업데이트 전 값, 업데이트 후 값을 프로퍼티로 갖는 람다식을 적어야 함
     */
    var temperature: Double by observable(20.0) { _, old, new ->
        if (temperature < 25) {
            println("온도 업데이트: $old -> $new")
        } else {
            println("온도가 너무 높습니다! 경고!: $old -> $new")
        }
    }
}

fun main() {
    val thermostat = Thermostat()
    thermostat.temperature = 22.5 // 온도 업데이트: 20.0 -> 22.5
    thermostat.temperature = 25.0 // 온도가 너무 높습니다! 경고!: 22.5 -> 25.0
}
