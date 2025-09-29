package intermediate.properties

/**
 * 코틀린 표준 라이브러리를 쓰면 아까 본 위임 프로퍼티처럼 일일이 getValue와 setValue 지정 불필요
 * 바로 lazy : 코틀린 표준 라이브러리 제공 함수
 *
 * 처음 접근할 때만 초기화되는 프로퍼티
 * 이후에는 최초 계산된 값을 그대로 재사용합
 * lazy() 함수와 람다 표현식을 사용하여 정의
 * 기본적으로 스레드 안전(thread safe)하게 동작
 */

class Database {
    fun connect() = println("데이터베이스 연결중...")
    fun query(sql: String): List<String> = listOf("데이터 1", "데이터 2", "데이터 3")
}

// lazy 프로퍼티 정의
val databaseConnection: Database by lazy {
    val db = Database()
    db.connect()
    db // 코틀린 람다의 마지막 라인이 반환 + 반환 타입이 Lazy<T>, T는 프로퍼티 타입으로 여기서는 Database
}

fun fetchData() {
    val data = databaseConnection.query("SELECT * FROM DB")
    println("데이터들 : $data")
}

// 요약하자면 스레드 안전 지연 초기화 위임 프로퍼티..
fun main() {
    fetchData()
    // 데이터베이스 연결중...
    // 데이터들 : [데이터 1, 데이터 2, 데이터 3]

    println("--------")

    fetchData()
    // 데이터들 : [데이터 1, 데이터 2, 데이터 3]
}