package intermediate.properties

// https://kotlinlang.org/docs/kotlin-tour-intermediate-properties.html#delegated-properties
/**
 * 메소드 위임처럼, 프로퍼티도 위임 가능
 * 정확히는 프로퍼티의 getter와 setter 동작을 다른 객체에게 위임시키는 것
 * 주로 복잡한 저장 로직(DB, 맵, 세션)이 필요할 때 활용한다는데... 잘 모르겠다
 */

// var displayName: String by Delegate
// -> 보통 이런 식이며 Delegate 객체는 getValue() 함수를 + var니까 setValue()까지 제공 필요
class Cache {
    var cache: String? = null // 캐시 저장용 프로퍼티

    // 이런 식으로 operator, getValue(thisRef: Box, property: Any?) 에약어를 써야 함
    // thisRef의 타입은 위임받을 대상의 타입
    // property는 위임 프로퍼티 자체를 나타내는 메타데이터(프로퍼티 명칭과 타입 등등)
    operator fun getValue(thisRef: Box, property: Any?): String {
        if (cache == null) {
            cache = "${thisRef.content}" // 캐시가 없으면 저장
            println("캐시에 없어서 저장함: $cache")
        } else {
            println("캐시에서 꺼내와서 출력: $cache") // 캐시에서 꺼내옴
        }
        return cache?: "Unknown"
    }

    operator fun setValue(thisRef: Box, property: Any?, value: String) {
        cache = value // 캐시 갱신
        thisRef.content = value // Box 내부 content도 갱신
        println("캐시에 저장과 동시에 지정: $value")
    }
}

class Box(var content: String) {
    var intro: String by Cache() // Cache에 위임
}

fun main() {
    val box = Box("초밥 5세트")
    box.intro // 캐시에 없어서 저장함: 초밥 5세트
    box.intro // 캐시에서 꺼내와서 출력: 초밥 5세트
    box.intro = "라면 3그릇" // 캐시에 저장과 동시에 지정: 라면 3그릇
}

/**
 * 위임 프로퍼티 자체로써는 초기화가 불필요하다
 * 어차피 다른 객체에게 위임해서 갖고 올 거니까(실제 값을 위임 대상이 관리하고, 위임 프로퍼티 자체는 백킹 필드 x)
 *
 * lazy 프로퍼티, 관찰 가능 프로퍼티 전부 포함
 */