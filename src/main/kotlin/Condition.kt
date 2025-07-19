fun main() {
    val input = readln()
    val inputAsInteger = input.toIntOrNull()

    // result1, result2 둘 다 같은 문법

    if (inputAsInteger != null) {
        val result1 = if (inputAsInteger % 2 == 0) {
            "this is odd"
        } else if (inputAsInteger < 10) {
            "this is smaller than 10"
        } else if (inputAsInteger < 100) {
            "this is smaller than 100"
        } else {
            "this is number"
        }

        print("output1 : ${result1}\n")

        val result2 = when {
            inputAsInteger % 2 == 0 -> "this is odd"
            inputAsInteger < 10 -> "this is smaller than 10"
            inputAsInteger < 100 -> "this is smaller than 100"
            else -> "this is number"
        }

        print("output2 : ${result2}")
    }
}