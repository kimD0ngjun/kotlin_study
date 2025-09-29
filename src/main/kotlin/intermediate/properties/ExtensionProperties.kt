package intermediate.properties

// https://kotlinlang.org/docs/kotlin-tour-intermediate-properties.html#extension-properties
// 확장 함수처럼 확장 속성도 존재
// 단 얘는 백킹 필드가 없음. 즉, getter와 setter를 직접 작성해야 한다는 것

data class Me(val firstName: String, val lastName: String)

var Me.fullName: String
    get() = "${this.lastName} ${this.firstName}"
    set(value) {
        // setter 정의야 뭐 가능하지만, 얜 내부 백킹 필드에는 접근 못하니 의미는 없음..
    }

fun main() {
    val me: Me = Me("동준", "김")
    println("안녕하세요, 제 이름은 ${me.fullName}입니다")
}