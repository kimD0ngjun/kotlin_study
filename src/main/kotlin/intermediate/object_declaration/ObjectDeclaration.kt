package intermediate.object_declaration

// https://kotlinlang.org/docs/kotlin-tour-intermediate-objects.html#object-declarations
/**
 * 객체 선언 : 클래스 선언과 '단일 인스턴스' 생성을 동시에 수행하는 방식
 * 이른바 아주 쉬운 싱글톤 구현이라 스레드 안전
 * 심지어 '지연 초기화'를 보장함
 */

// object 키워드를 쓰면 객체 선언 가능
object DatabaseConnection {
    val url = "jdbc:mysql://localhost:3306/test_db"
    fun connect(): Unit = println("$url 연결 완료")
}

// 객체 선언은 상속과 구현을 받을 수 있다
interface InterfaceExample {
    fun example(): Unit
}
abstract class AbstractClassExample(val name: String) {
    abstract fun example(): Unit
}

object example1: InterfaceExample {
    override fun example() {
        println("인터페이스 구현도 가능")
    }
}

object example2: AbstractClassExample("항상 생성자 호출 필요") {
    override fun example() {
        println("생성자가 있는 추상 클래스는 항상 생성자 호출하며 상속도 가능")
    }
}

fun main() {
    // 객체 선언은 생성자를 가지지 않는다. 고로 객체 선언 명칭 그대로 접근한다
    println("연결하려는 DB URL: ${DatabaseConnection.url}")
    DatabaseConnection.connect()
}