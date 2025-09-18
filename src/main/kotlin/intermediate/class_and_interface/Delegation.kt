package intermediate.class_and_interface

/**
 * 위임(delegation)은 "내가 직접 기능을 구현하지 않고, 다른 객체에 맡긴다"라는 것
 * 인터페이스는 유용하지만, 인터페이스에 함수가 너무 많으면 하위 클래스에 반복적인 코드가 많이 생길 수 있음
 * 클래스 동작의 일부만 재정의하려는 경우에도 동일한 코드를 반복해서 작성해야 함
 *
 * 보일러플레이트란 소프트웨어 프로젝트의 여러 부분에서 거의 또는 전혀 변경 없이 재사용되는 코드 조각을 말합니다.
 */

interface DrawingTool {
    val color: String
    fun draw(shape: String)
    fun erase(area: String)
    fun getToolInfo(): String
}

// 이런 식의 인터페이스 구현에서,
// 사실상 색깔만 다를뿐 동일 기능의 보일러플레이트를 추가로 구현해야 할 수도 있다
class PenTool : DrawingTool {
    override val color: String = "검은색"

    override fun draw(shape: String) {
        println("${shape}을 ${color}로 그리기")
    }

    override fun erase(area: String) {
        println("$area 영역 지우기")
    }

    override fun getToolInfo(): String {
        return "펜입니다. 색은 $color"
    }
}

// 그래서 아예 도구 역할을 하는 클래스에 의존성 주입을 하는 경우를 생각할 수 있다
// 하지만 이것도 보일러 플레이트의 근본적 한계를 벗어날 수는 없다.
// 그래서 '위임'이 필요하다
class CanvasSession(val tool: DrawingTool): DrawingTool {
    override val color: String = "파란색"

    // 일일이 작성하는 위임 코드들....
    override fun draw(shape: String) {
        tool.draw(shape)
    }

    // 일일이 작성하는 위임 코드들....
    override fun erase(area: String) {
        tool.erase(area)
    }

    // 일일이 작성하는 위임 코드들....
    override fun getToolInfo(): String {
        return tool.getToolInfo()
    }
}

/**
 * 위임은 by 키워드로 이뤄진다
 * by 문법은 인터페이스 구현이나 추상 클래스 상속에서만 사용한다
 * 보통 생성자 주입 기반의 위임이 일반적임
 * 일부 추가 재구현도 가능
 */
class CanvasSession_Delegation(val tool: DrawingTool): DrawingTool by tool {
    // 이런 식의 추가 재구현도 가능
    override val color: String = "초록색"

    override fun erase(area: String) {
        println("얘는 안 지워용")
    }
}

/**
 * 이런식으로 직접 인스턴스 생성에서 by 처리도 가능
 */
class CanvasSession_Example: DrawingTool by PenTool()

fun main() {
    val penTool = PenTool()
    val canvasSession = CanvasSession_Delegation(penTool)
    canvasSession.draw("동그라미") // 재정의하지 않았으므로 위임된 검정색으로 동그라미 그림
    canvasSession.erase("전체 영역") // 재정의됐으므로 얘는 안 지워용
}