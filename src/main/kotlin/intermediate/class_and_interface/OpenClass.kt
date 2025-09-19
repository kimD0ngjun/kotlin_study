package intermediate.class_and_interface

// https://kotlinlang.org/docs/kotlin-tour-intermediate-open-special-classes.html#open-classes
/**
 * 코틀린은 클래스가 기본적으로 상속이 불허돼서 추상 클래스나 인터페이스를 써야 함
 * 그럼에도, 일반 클래스를 상속시키고 싶으면 open 키워드로 상속 제한을 개방
 */

open class Vehicle(val make: String, val model: String)

/**
 * 자바식의 표현대로라면
 * class Car extends Vehicle {
 *     Car(String make, String model) {
 *         super(make, model);
 *     }
 * }
 * 와 같
 * 자식 클래스 생성자 파라미터에 변수 선언 키워드가 없으면 단순히 전달용 매개변수
 */
class Car(make: String, model: String, val numberOfDoors: Int): Vehicle(make, model)

/**
 * open 키워드로 상속했다 해도, 해당 부모클래스의 멤버를 함부로 재정의할 수는 없다
 * open 키워드가 멤버 앞에도 붙어야만 가능하다
 */
open class Parent {
    val field1: String = "멤버 필드"
    open val field2: String = "재정의 가능한 필드"

    fun method(): Unit = println("멤버 메소드")
    open fun method2(): Unit = println("재정의 가능한 메소드")
}

class Child: Parent() {
//    val field1: String = "" // 자바랑 다르게 아예 같은 이름의 필드를 허용치 않음
    override val field2: String = "재정의한 멤버 필드"

    override fun method2(): Unit = println("재정의된 멤버 메소드")
}

/**
 * 다만 위의 예제처럼 속성에 대해서는 직접 접근해서 override 키워드를 덧붙이는 것이 코틀린스럽지 않다
 * 상속을 받으면서 상위 생성자 호출에서 직접 재정의할 프로퍼티 값을 부여한다(여기서는 nation 멤버 필드)
 */
open class KotlinParent(val name: String, val gender: String, val nation: String = "World")
class KotlinChild(name: String, gender: String, val age: Int): KotlinParent(name, gender, "Korea")

fun main() {
    val car = Car("도요타", "벤츠", 4)
    println("제조사 : ${car.make}\n모델명 : ${car.model}\n문 갯수: ${car.numberOfDoors}")

    // 동적 바인딩은 자바나 코틀린이나 똑같이 적용
    val child: Parent = Child()
    println(child.field1) // 멤버 필드
    println(child.field2) // 재정의한 멤버 필드
    child.method() // 멤버 메소드
    child.method2() // 재정의된 멤버 메소드

    // 타입을 KotlinParent라고 다형성을 꾀하다가 age에는 접근조차 못하는 불상사... 자바에서 많이 접했던
    val me = KotlinChild("김동준", "남성", 30)
    println(me.name) // 김동준
    println(me.gender) // 남성
    println(me.age) // 30
    println(me.nation) // Korea -> 바로 얘가 코틀린스럽게 프로퍼티 재정의한 것
}