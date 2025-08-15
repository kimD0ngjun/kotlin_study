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

fun main() {
    val student1 = Student(1, "John") // 주 생성자 기반
    val student2 = Student(2) // 보조 생성자 기반

    println(student1.name)
    student1.name = "김동준"
    println(student1.name)
//    student1.id = 2

    val person = Person("김모씨")
    person.talk()

    // https://kotlinlang.org/docs/kotlin-tour-classes.html#data-classes
}