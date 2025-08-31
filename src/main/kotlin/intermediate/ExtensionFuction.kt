package intermediate

// 기존 자바에서는 기존 클래스에 함수를 추가하려면 상속이나 직접 OCP 위반하던지 등등...
// 근데 그건 그렇고, 만약 외부 라이브러리 같은 경우면 더 추가하기 빡세짐
// 코틀린은 그럴 필요 없이 '확장'이라는 개념 제시(애초에 코틀린은 원천적으로 클래스가 상속 불허)

/**
 * fun 확장할 클래스.함수명: 리턴타입 {
 *    return 리턴값
 * }
 */

// 단, 확장함수는 수신객체의 private이나 protected 멤버를 사용할수 없기 때문에, 해당 수신객체의 public 멤버에만 접근이 가능
// 애시당초 외부에서 접근하는 구조이므로...
// 참고로 확장함수는 클래스의 일부가 아니므로 오버라이딩이 안된다. 다만 오버로딩은 가능
// 확장함수의 이름이 멤버 메소드명과 같으면 멤버 메소드 우선 실행
// 확장함수는 정적 바인딩이다(상속의 필드와 메소드 차이 생각하기)

// 문자열 클래스에 존재하지 않고, 내가 직접 커스텀한 볼드체 처리 메소드
fun String.bold(): String = "<strong>$this</strong>"

// 상당히 유연하면서도 캡슐화를 깨뜨리지 않기 때문에 매우 유용
class HttpClient {
    fun request(method: String, url: String, headers: Map<String, String>): HttpResponse {
        println("Requesting $method to $url with headers: $headers")
        return HttpResponse("Response from $url")
    }
}

class HttpResponse(val response: String)

fun HttpClient.get(url: String): HttpResponse = request("GET", url, emptyMap())

fun main() {
    println("hello".bold())

    val client = HttpClient()

    // Making a GET request using request() directly
    val getResponseWithMember = client.request("GET", "https://example.com", emptyMap())

    // Making a GET request using the get() extension function
    val getResponseWithExtension = client.get("https://example.com")
}