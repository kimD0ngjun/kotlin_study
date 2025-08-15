package grammer

fun main() {
//    val input = readln()
//    val inputAsInteger = input.toIntOrNull()
//
//    // result1, result2 둘 다 같은 문법
//
//    if (inputAsInteger != null) {
//
//        // 자바의 if는 단순 '문(statement)'인 반면
//        // 코틀린의 if는 표현식(expression)이다. 그래서 변수 할당이 가능하다
//        // 표현식으로 쓰인 if는 무조건 else를 요구한다. 모든 경우에서 값을 변수에 할당해야 하므로
//
//        val result1 = if (inputAsInteger % 2 == 0) {
//            "this is odd"
//        } else if (inputAsInteger < 10) {
//            "this is smaller than 10"
//        } else if (inputAsInteger < 100) {
//            "this is smaller than 100"
//        } else {
//            "this is number"
//        }
//
//        println("output1 : ${result1}")
//
//        // 계산식 조건용
//        val result2 = when {
//            inputAsInteger % 2 == 0 -> {
//                println("람다처럼 보이지만 그저 조건 분기와 결과를 매핑할 뿐, 람다는 아니에요")
//                // 하지만 람다와 유사하게 볼 코틀린만의 논점이 있는데...
//                "this is odd"
//            }
//            inputAsInteger < 10 -> "this is smaller than 10"
//            inputAsInteger < 100 -> "this is smaller than 100"
//            else -> "this is number"
//        }
//
//        println("output2 : ${result2}")
//
//        // 값 매칭용
//        val result3 = when(inputAsInteger) {
//            3 -> "this is three"
//            5 -> "this is five"
//            in 6 .. 10 -> "this is number between 6 and 10"
//            11, 12, 13 -> "this is.. 11? 12? 13?"
//            else -> "this is number"
//        }
//
//        println("output3 : ${result3}")
//    }
    val obj = "Hello"

    // when이 문장(statement)으로 작성되면 else가 불필요
    when (obj) {
        "Hi" -> println("이것은 Hi")
        "Hello" -> println("이것은 Hello")
    }

    // when이 표현(expression)으로 작성되면 else 필요(무족헌 할당해야되니깐)
    // 다시 말하지만, -> 는 람다식이 아녜용
    val result = when (obj) {
        "Hi" -> println("이것은 Hi")
        else -> println("이것은 Hello")
    }

    val trafficLightState = "Red" // This can be "Green", "Yellow", or "Red"

    val trafficAction = when {
        trafficLightState == "Green" -> "Go"
        trafficLightState == "Yellow" -> "Slow down"
        trafficLightState == "Red" -> "Stop"
        else -> "Malfunction"
    }

    println(trafficAction)

    val trafficLightState2 = "Red" // This can be "Green", "Yellow", or "Red"

    val trafficAction2 = when (trafficLightState2) {
        "Green" -> "Go"
        "Yellow" -> "Slow down"
        "Red" -> "Stop"
        else -> "Malfunction"
    }

    println(trafficAction2)
    // Stop

    val a = 1
    val b = 2
    val result1 = if (a > b) {
        "더 큰 숫자는 $a"
    } else {
        "더 큰 숫자는 $b"
    }
    println("result1 is $result1")

    // 더 간단한 표현식
    val text = if (a > b) "더 큰 숫자는 $a" else "아니, 더 큰 숫자는 $b"
    println(text)


}