package intermediate.lambda

// https://kotlinlang.org/docs/kotlin-tour-intermediate-lambdas-receiver.html

/**
 * 자바에서 람다를 표현하던 () -> {} 이건 사실 자바의 문법적 설탕이다
 * 즉, 실제로 컴파일되면 new 인터페이스 {구현...} 이런 식으로 바뀌게 되지, 실제 함수형 프로그래밍의 람다와는 약간 다름
 * 말인 즉슨, 어떠한 함수형 인터페이스에 귀속되어야만 람다가 존재할 수 있다
 *
 * 반면, 아까 예시에서 봤듯 코틀린은 그냥 타입 정의만으로도 끝날 수 있다
 * 문법적으로는 중괄호에 화살표 문법을 적어줌으로써 코틀린의 람다식이 완성된다
 */

/**
 * 위의 내용에서, 확장 함수에서 봤던 '리시버' 개념까지 람다에 갖춰질 수 있다
 * StringBuilder.() -> Unit 이게 곧 리시버 타입이고 얘 떄문에 it이니 this니 안적고도 바로 접근 가능
 */
val appendHello: StringBuilder.() -> Unit = {
    this.append("Hello")
    append(" World") // `this.` 생략 가능 -> 리시버 덕분
}

/**
 * MutableList<Int> 가 리시버가 되고
 * 파라미터와 반환값이 없는 람다식이 작성된다
 */
val example: MutableList<Int>.() -> Unit = {
}