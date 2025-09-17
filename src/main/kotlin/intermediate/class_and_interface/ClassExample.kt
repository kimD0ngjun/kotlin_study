package intermediate.class_and_interface

/**
 * 코틀린의 최상위 클래스는 'Any'
 * 모든 클래스는 궁극적으로 Any 클래스에서 상속받은 자식들임
 */

// 기본적으로 코틀린의 클래스는 상속이 불허된다
// 그래서 상속을 활용하려면 추상 클래스 사용을 고려해보자. 추상 클래스는 디폴트로 상속이 된다
// 코틀린 추상 클래스는 생성자를 가지지만, 인스턴스를 생성할 수 없다

// 추상 클래스
abstract class Animal(val name: String) {
    // 추상 필드... 라지만 getter 구현을 강제하는 추상 멤버
    abstract val sound: String

    // 추상 메소드
    abstract fun makeSound()

    // 모든 구현 자식에서 사용 가능
    fun animalInfo() {
        println("이 동물의 정체는 ${name}!")
    }
}

/**
 * 상속은 extends가 아닌, ':'를 사용한다.
 * 상위 클래스가 생성자가 있으면 하위 클래스에서 상속할 때, 반드시 콜론 뒤에 생성자 호출이 필요하다
 * 사실 디폴트가 그렇지만, 매개변수 없는 기본 생성자라면 () 생략 가능
 * name은 이미 상위 추상체에서 선언됐기 때문에 굳이 변수 선언 키워드 안 붙여도 됨
 */
class Dog(name: String, val sex: String): Animal(name) {
    override val sound: String = "멍멍"
    override fun makeSound() {
        println("${this.sex} 성별의 강아지가 ${this.sound} 하고 짖는다")
    }
}

fun main() {
    /**
     * 추상 클래스는 으레 자바가 그렇듯, 단일 상속만 지원한다
     */
    val dog = Dog("바둑이", "수컷")
    dog.animalInfo()
    dog.makeSound()
}