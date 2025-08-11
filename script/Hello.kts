/*
dependencies {
    implementation(kotlin("script-runtime"))
}

이거를 추가해야 kts 실행 가능
그리고 src 패키지는 JVM의 컴파일 대상인데, kts는 런타임 스크립트라서 JVM 컴파일 대상이 x
그래서 src 패키지 밖의 프로젝트 하부 별도 패키지에 런타임용 kts 작성 필요

참고: build.gradle.kts는 Gradle 빌드 스크립트 내부에 kts 실행엔진이 내포되어있음
그래서 의존성 추가 없이도 실행 가능했던 거
 */

println("hello in kts") // top level에서 바로 실행문 쓸 수 있음(kt와의 차이)

val a = 1
val b = 2

println(a + b)

/*
보통 kts는 간단한 자동화, 테스트 스크립트
도구나 플러그인 스크립팅 등에 활용됨
 */