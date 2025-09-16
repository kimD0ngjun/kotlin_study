package intermediate

// https://kotlinlang.org/docs/kotlin-tour-intermediate-scope-functions.html#apply

class Client() {
    var token:String? = null
    fun connect() = println("connect")
    fun authenticate() = println("authenticate")
    fun getData(): String = "data"
}

/**
 * 지금이야 간단한 예제지만, 인스턴스 초기화 시간이 길어지면 런타임에서 인스턴스 변수로 메소드와 필드를 호출할 때
 * 잘못하면 문제가 발생할 수도 있음(비정합성 이슈)
 */
var client = Client()

// apply scope 함수를 사용하면 코드 내에서 동일한 위치에서
// 클래스 인스턴스에 대한 멤버 함수를 생성, 구성 및 사용할 수 있음
var clientApply = Client().apply {
    // this 생략 가능
    token = "asdf"
    connect()
    authenticate()
} // apply는 객체 자체를 반환하므로 후속작업(추가 메소드 호출 같은)을 이을 수 있음

fun main() {
    client.token = "asdf"
    client.connect()
    client.authenticate()
    println(client.getData())

    println(clientApply.getData())
}