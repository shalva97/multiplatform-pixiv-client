# PixivClient

A pixiv API library based on Kotlin Multiplatform. Uses ktor client as HttpClient.
This client is based on the api of pixiv mobile app.

## Installation
Before installing, authenticate with GitHub Package Registry. See "[Configuring npm for use with GitHub Package Registry](https://help.github.com/en/articles/configuring-npm-for-use-with-github-package-registry#authenticating-to-github-package-registry)."

Add repository
```kotlin
maven {
    url = uri("https://maven.pkg.github.com/shalva97/multiplatform-pixiv-client")
    credentials {
        username = properties["gpr.user"] as String? ?: System.getenv("USERNAME")
        password = properties["gpr.key"] as String? ?: System.getenv("TOKEN")
    }
}
```
Add dependency
```
implementation("io.github.shalva97:pixiv-client:2.0.4")
```
