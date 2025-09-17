package intermediate.lambda

// 메뉴 아이템과 메뉴 클래스
class MenuItem(val name: String)

class Menu(val name: String) {
    val items = mutableListOf<MenuItem>()

    fun item(name: String) {
        items.add(MenuItem(name))
    }
}

fun menu(name: String, init: Menu.() -> Unit): Menu {
    val menu = Menu(name)
    menu.init() // 리시버의 멤버를 호출할 수 있게 됨
    return menu
}

fun printMenu(menu: Menu) {
    menu.items.forEach {
        println("메뉴: ${it.name}")
    }
}

fun main() {
    /**
     * 함수의 마지막 파라미터가 람다일 떄,
     * 괄호 밖으로 빼서 가독성을 높이는 DSL 문법 식으로 사용이 가능하다
     */
    var menu = menu("점심") {
        item("햄버거")
        item("라면")
        item("초밥")
    }

    // 사실 얘와 똑같은 표현이다. 즉, 문법적 설탕이었던 셈
    var original = menu("점심", {
        item("햄버거")
        item("라면")
        item("초밥")
    })

    printMenu(menu)
    printMenu(original)
}

/**
 * 리시버를 가진 람다 표현식은 Kotlin의 타입 안전 빌더와 결합하여
 * 런타임이 아닌 컴파일 시점에 타입 문제를 감지하는 DSL을 만들 수 있습니다.
 * 자세한 내용은 타입 안전 빌더를 참조하세요.
 */