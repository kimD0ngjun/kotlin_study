package intermediate.class_and_interface

// https://kotlinlang.org/docs/kotlin-tour-intermediate-classes-interfaces.html#interfaces

interface Payment {
    // 자바에서도 인터페이스에 abstract 안 붙인 것처럼 여기도 마찬가지
    fun pay(amount: Double): String
}

interface PayType {
    // 자바에서는 전역 변수처럼 취급됐던 인터페이스 필드 선언...
    /**
     * 코틀린은 멤버 필드가 아니라 getter의 추상 메소드가 선언된 것과 같
     * val일 떄는 불변 변수이니 getter만 추상 메소드
     * var일 때는 가변 변수이니 getter, setter 추상 메소드
     */
    val type: String
}

// 인터페이스는 생성자가 당연히 없으므로 콜론 뒤의 인터페이스에 ()를 붙이지 않는다
class CreditCard(val cardNumber: String, val cardHolderName: String): Payment, PayType {
    // 역시나 오버라이딩은 override 키워드로
    override fun pay(amount: Double): String {
        return "끝자리 ${cardNumber.takeLast(4)}인 카드로 $amount 원 결제"
    }

    override val type: String = "credit card"
}

fun main() {
    val card = CreditCard("1234 5678 9012 3456", "김동준")
    println(card.pay(100.0))
    println("결제 수단 타입은 ${card.type}")
}