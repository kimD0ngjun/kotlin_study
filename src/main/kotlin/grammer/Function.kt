package grammer

/**
 * 공식 문서에서 함수명은 소문자 어두의 카멜 케이스를 권장함
 */

fun hello () {
    println("hello")
}

fun sum(a: Int, b: Int): Int {
    return a + b;
}

// 파라미터: 타입 = 디폴트 값
fun printMessageWithPrefix(message: String = "no message", prefix: String = "INFO"): Unit {
    println("[$prefix] $message")
}

/**
 * 코틀린은 void가 없고 대신 Unit이 차지함
 * 자바의 void와의 차이점은, void는 타입이 아닌 반면, Unit은 하나의 값이 하나 존재하는 타입임
 * 바이트코드로 변환시, 자바의 void로 치환돼서 호환 가능
 * 참고로 자바의 Void는 타입이긴 한데, void에 대응하기 위한 억지 타입 느낌...
 */
fun unitExample(): Unit {
    println("이것은 Unit 타입 반환 함수")
//    return Unit
//    return
}

// 약간 람다식 느낌 나는 함수 작성(return도 생략되네)
// 할당 연산자 =를 사용하는 경우 Kotlin은 유형 추론을 사용하므로 반환 유형을 생략 가능
fun power(x: Int): Int = x * x

// A list of registered usernames
val registeredUsernames = mutableListOf("john_doe", "jane_smith")

// A list of registered emails
val registeredEmails = mutableListOf("john@example.com", "jane@example.com")

fun registerUser(username: String, email: String): String {
    // Early return if the username is already taken
    if (username in registeredUsernames) {
        return "Username already taken. Please choose a different username."
    }

    // Early return if the email is already registered
    if (email in registeredEmails) {
        return "Email already registered. Please use a different email."
    }

    // Proceed with the registration if the username and email are not taken
    registeredUsernames.add(username)
    registeredEmails.add(email)

    return "User registered successfully: $username"
}

fun main() {
    hello()
    println(sum(1, 2))
    printMessageWithPrefix("stack overflow!", "WARN")
    // 파라미터에 디폴트 값 적용
    printMessageWithPrefix("nothing happened")
    printMessageWithPrefix()
    printMessageWithPrefix(prefix = "SYSTEM") // 건너뛴 파라미터의 값 지정은 키, 값 형식 필요
    // 파라미터 키, 값 형식도 가능
    printMessageWithPrefix(message = "update completed", prefix = "UPDATE")
    unitExample()

    println(registerUser("john_doe", "newjohn@example.com"))
    // Username already taken. Please choose a different username.
    println(registerUser("new_user", "newuser@example.com"))
    // User registered successfully: new_user
}