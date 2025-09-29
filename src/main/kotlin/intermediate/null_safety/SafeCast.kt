package intermediate.null_safety

// https://kotlinlang.org/docs/kotlin-tour-intermediate-null-safety.html#as-and-as-operators

fun main() {
    /**
     * 세이프 캐스팅은 널 안전성과 관련이 있다
     * as 키워드가 명시적 형변환에 쓰이는데, 이 형변환 결과가 nullable -> non nullable도 가능
     * 근데 이러면 런타임 오류 발생 가능
     */
//    // nullable
//    val x: String? = null
//    // non nullable
//    val y = x as String // -> NullPointerException: null cannot be cast to non-null type kotlin.String 발생
//
//    print(y)

    /**
     * 그래서 as? 키워드를 써서 널 가능성 변수의 타입 캐스팅에서 NPE 발생을 막는다
     */
    // nullable
    val a: String? = null
    // non nullable
    val b = a as? String // -> 명시적 형변환이 사실상 실패했지만 안전하게 null을 반환시킨다

    print(b) // null 출력
}

// 보통 널 안전 연산자랑 조합해서 쓰면 표현이 간단해진다
fun getLenSum(items: List<Any>): Int {
    var total = 0

    for (item in items) {
        total += if (item is String) {
            item.length
        } else {
            0 // 문자열 타입이 아니면 그냥 0 더함
        }
    }

    return total
}

// 위처럼 장황한 걸 아래처럼 축약 가능
fun getLenSumWithElvis(items: List<Any>): Int = items.sumOf { (it as? String)?.length ?: 0 }
/**
 * sumOf{} : 고차 함수, 람다를 인자로 받
 * 람다의 매개변수가 1개뿐일 때는 그 파라미터 이름을 생략하고 자동으로 it이라는 디폴트 이름 사용 가능
 *
 * as? 세이프 캐스팅 : 타입 캐스팅이 실패해도 안전히 null 반환
 * ?. 세이프 콜 : String이면 length 호출, 아니면 null 반환
 * ?: 엘비스 연산자 : 해당 결과가 null이면 0으로 반환
 */