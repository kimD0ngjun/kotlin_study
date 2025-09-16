package intermediate.lambda

// java.LambdaExample 이랑 비교해보기
fun main() {
    // 자바와의 가장 큰 차이점 : 람다가 객체가 아닌 일급 함수임
    // 즉, 함수 타입으로 바로 정의가 가능해짐
    val directLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(directLambda(1, 2))

    // 물론 이런 식으로 기존 자바 스타일처럼 작성할 수도 있음
    val lambda = Lambda {x, y -> x + y}
    println(lambda.plus(1, 2))
}


// 코틀린에서는 일반 인터페이스는 추상 메소드가 1개여도 함수형 인터페이스가 안됨
//interface Lambda {
//    fun plus(a: Int, b: Int): Int
//}

// 이런 식으로 SAM 인터페이스로 적어야 함
fun interface Lambda {
    fun plus(a: Int, b: Int): Int
}