package intermediate.class_and_interface

// https://kotlinlang.org/docs/kotlin-tour-intermediate-properties.html

/**
 * Kotlin에서 속성은 값을 가져오고 수정하는 작업을 처리하는 기본 get() 및 set() 함수(속성 액세서)를 가지고 있습니다.
 * 이러한 기본 함수는 코드에서 명시적으로 보이지는 않지만, 컴파일러가 속성 액세스를 처리하기 위해 자동으로 생성합니다.
 * 이 액세서들은 실제 속성 값을 저장하기 위해 백킹 필드를 사용합니다.
 * - by 코틀린 공식문서
 *
 * 저 말이 별건 아니고, 컴파일러가 프로퍼티에 자동으로 '접근자 함수'가 세팅해줌
 * 백킹 필드 : 실제 데이터를 저장하는 숨은 필드
 */

class Contact(val id: Int, var email: String) {
    var category: String = ""
        get() = field // 참고로 이 field는 코틀린의 예약자라서 내가 임의로 못 바꿈
        set(value) {
            field = value
        }

    /**
     * 실제 클래스 멤버 프로퍼티를 지정하고 개행 후에 g나 s 치면 자동으로 get이랑 set 함수가 세팅되려고 함
     * 이게 접근자 함수이며, 실제 값은 field라 칭해져있는 백킹 필드에 저장되어 있는 거
     * 참고로 위의 구현은 기본 구현 형식이다. 즉, 내가 직접 접근자 함수 로직을 커스텀할 수 있다
     */
}

/**
 * 커스텀 예시
 */
class Person {
    var name: String = ""
        get() = field
        set(value) {
            // 세터 적용을 위해 필드를 찾아가려고 하는데,
            // 그 필드는 name 안에 있으니 계속 name 안으로 컴파일러가 파고드는데,
            // 보이는 건 name만 있으니 계속 파고들어서 스택오버플로우가 발생한다
//            name = value.replaceFirstChar { firstChar -> firstChar.uppercase() }
            field = value.replaceFirstChar { firstChar -> firstChar.uppercase() }
        }
}

fun main() {
    val person = Person()
    person.name = "kim"
    println(person.name)
}