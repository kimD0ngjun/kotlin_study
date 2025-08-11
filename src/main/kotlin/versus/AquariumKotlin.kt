package versus

data class AquariumKotlin(var mTemperature: Int = 0)

fun main() {
    val aq = AquariumKotlin()
    aq.mTemperature = 25

    println(aq.mTemperature)
}