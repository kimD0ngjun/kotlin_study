## 헷갈리는 범위 함수와 람다 관계
- 내가 알고 있는 람다식은 `T -> something` 이런 식의 화살표 기호 있는 놈
- 근데 확장 함수는 좀 다름

### 확장 함수의 상위 모듈
#### let

- `let`을 거슬러 타고 올라가면...
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> T.let(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(this)
}
```
- 이런 놈이 나옴
- 참고로 `inline` 키워드는 함수 호출 치환 키워드. JVM에 호출 스택 안 쌓이게 해서 함수 호출 오버헤드 제거한다고 하는데.. 얘도 나중에 알아보기
- 일반적으로 코틀린에서는
  - 파라미터가 있으면 `(Int, Int) -> Int` 이런 식
  - 파라미터가 없으면 `doSomething()` 이런 식
- 보면, 확장함수인 `T.let`이 람다식 `(T) -> R` 타입인 `block`을 파라미터로 받고 있음
- 반환값은 `block(this)`, 즉 파라미터로 받은 람다를 실행하는 것
```kotlin
val result = "hello".let {
    it.length
}
```
- 여기서는 `it.length` + 중괄호가 람다 블록인 것임.

#### apply
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T> T.apply(block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
```
- 얘의 파라미터 `block`은 `T.() -> Unit` 타입
- `T.() -> R 은` `T` 객체를 수신 객체(`this`)로 받아 `R`로 반환함을 의미
- 그래서 `this`가 이미 `T` 객체를 가리키고, 호출자 자기 자신을 그대로 반환할 수 있게 됨(타입은 이미 호출자 시점에서 결정 끝)

### run
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> T.run(block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}
```
- 반환 결과가 `R`, 즉 람다의 실행 결과를 반환하게 됨

### also
```kotlin
@kotlin.internal.InlineOnly
@SinceKotlin("1.1")
public inline fun <T> T.also(block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(this)
    return this
}
```
- `also`는 `apply`랑 비슷해보이지만, `this`를 람다 파라미터로 넘긴다는 차이가 있음
- 수신 객체에서는 `it`으로 받아들이는 식으로 코드 작성하게 될 거임

### with
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return receiver.block()
}
```
- `with` 구조가 좀 괴상망측한데, 결과적으로 `run`과 똑같이 동작한다
- `run`은 수신 객체에서 블록 실행하고 결과 반환, `with`은 외부에서 전달받아 결과를 전하는 계산 컨텍스트 목적
