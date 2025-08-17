package grammer

class Customer // declare

// class properties
class Contact(val id: Int, var email: String) // 심플한 프로퍼티 선언
// 주 생성자 -> 보조 생성자 -> init 블록
class Student(val id: Int, var name: String) {  // 주 생성자
    var email: String

    // 보조 생성자 (자바 스타일)
    constructor(id: Int) : this(id, "Unknown") {
        println("[보조 생성자] 이름이 없어서 기본값 'Unknown'으로 설정")
    }

    // init 블록 - 주 생성자 실행 후 동작
    init {
        println("[init] Student 객체 생성 중...")
        // 주 생성자에서 받은 name을 가공
        name = name.trim().replaceFirstChar { it.uppercase() }
        email = "$name@example.com"
        println("[init] 초기화 완료: id=$id, name=$name, email=$email")
    }
}

class Person(val name: String) {
    fun talk() {
        println("내 이름은 $name")
    }
}

// 자바의 DTO와 비슷한, 하지만 유연한(Setter가 있다..!) 데이터 클래스
/**
 * 기본적인 class도 getter와 setter 등이 있지만
 *
 */
data class User(val name: String, var id: Int)

fun main() {
    val contact1 = Contact(1, "email@email.com")

    // 명시되지 않아도 getter 있음
    println(contact1.id)
    println(contact1.email)

    // 명시되지 않아도 setter 있음(물론 var 필드에만 가능)
//    contact1.id = 2
    contact1.email = "new_email@email.com"

    val student1 = Student(1, "John") // 주 생성자 기반
    val student2 = Student(2) // 보조 생성자 기반

    println(student1.name)
    student1.name = "김동준"
    println(student1.name)
//    student1.id = 2

    val person = Person("김모씨")
    person.talk()

    // https://kotlinlang.org/docs/kotlin-tour-classes.html#data-classes
    val user1 = User("John", 1)
    println(user1.name) // getter
//    user1.name = "Jack" // 물론 불변 선언(val) 필드는 Setter X
    println(user1.id)
    user1.id = 2
}