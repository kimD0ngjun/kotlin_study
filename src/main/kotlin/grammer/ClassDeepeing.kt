package grammer

// Kotlin은 동일 패키지 내에서 클래스 이름이 중복되면 Redeclaration 에러
// 코틀린에서는 .kt 파일 이름과는 상관없이, "같은 패키지 + 같은 클래스명" 조합이 충돌 여부를 결정

class PersonClass(val id: Int, var name: String)
data class PersonData(val id: Int, var name: String)

fun main() {
    // 클래스와 데이터 클래스는 얼핏 비슷하게 생겼다
    val person1 = PersonClass(1, "john")
    val otherPerson1 = PersonClass(2, "Sam")
    val anotherPerson1 = PersonClass(1, "Jack")
    val person2 = PersonData(1, "john")
    val otherPerson2 = PersonData(2, "Sam")
    val anotherPerson2 = PersonData(1, "Jack")

    // getter도 자동선언 되어있고..
    println("그냥 사람 클래스의 인스턴스 이름은 ${person1.name}이고, 아이디는 ${person1.id}이다.")
    println("사람 데이터 클래스의 인스턴스 이름은 ${person2.name}이고, 아이디는 ${person2.id}이다.")

    // setter도 잘 동작하고.. 물론 var 키워드에 대해서만
    person1.name = "Jack"
    person2.name = "Jack"
//    person1.id = 2
//    person2.id = 2

    /**
     * 데이터 클래스는 그냥 클래스에서 추가 멤버 함수들이 존재한다
     * 이러한 멤버 함수를 사용하면 인스턴스를 읽기 가능한 출력으로 쉽게 인쇄하고,
     * 클래스의 인스턴스를 비교하고, 인스턴스를 복사하는 등의 작업을 수행하는 등, 여러 부분을 자동적으로 처리 가능
     */

    /**
     * 정리하자면, 코틀린에는 자바의 Object 클래스에 대응되는 Any 클래스가 존재
     *
     * public open class Any {
     *     open operator fun equals(other: Any?): Boolean
     *     open fun hashCode(): Int
     *     open fun toString(): String
     * }
     *
     * 기본적으로 제공되는 저 메소드들 역시 오버라이드 가능
     * 다만 데이터 클래스는 '데이터 모델링'에 특화되므로 애시당초 저 오버라이딩이 컴파일러가 자동 생성해주는 셈
     */

    // toString : 클래스 인스턴스 프린팅
    println(person1) // grammer.PersonClass@19469ea2
    println(person2) // PersonData(id=1, name=Jack)

    // equals : 클래스 동등성 비교
    println("그냥 클래스 == 비교: ${person1 == anotherPerson1}") // false
    println("그냥 클래스 equals 비교: ${person1.equals(anotherPerson1)}") // false
    println("데이터 클래스 == 비교: ${person2 == anotherPerson2}") // true
    println("데이터 클래스 equals 비교: ${person2.equals(anotherPerson2)}") // true

    // copy: 쉽게쉽게 데이터 복사하기
    println(person2.copy(id = 500)) // PersonData(id=500, name=Jack)
    println(person2.copy(name = "Daniel")) // PersonData(id=1, name=Daniel)
}