# Pokemon App
A test assignment for VK Android Developer summer internship

## Installation
Clone this repository
```gradle
git clone https://github.com/KostyaRazboynik/vk-summer-internship-pokemon-2024-android.git
```

## Build Variants
You can choose debug build or release build, depending on whether you want to see the debug logs from [Logger](https://github.com/KostyaRazboynik/vk-summer-internship-pokemon-2024-android/blob/main/core/utils/src/main/kotlin/com/kostyarazboynik/utils/Logger.kt) and [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

## Secrets 
If you want to store api keys or secrets in the project, create secrets.properties in project root and add your secrets like:
```kotlin
BASE_URL="https://pokeapi.co/api/v2/"
API_KEY="<YOUR API KEY>"
```
Don't forget to add them to the [secrets.default.properties](https://github.com/KostyaRazboynik/vk-summer-internship-pokemon-2024-android/blob/main/secrets.default.properties) file with default values

## Technologies used

- [Retrofit](https://github.com/square/retrofit) + [OkHttp3](https://github.com/square/okhttp/tree/master) + [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Dagger2](https://github.com/google/dagger)
- [Kotlin Coroutines + Flow](https://github.com/Kotlin/kotlinx.coroutines)
- [Glide](https://github.com/bumptech/glide)
- [Detekt](https://detekt.dev/)
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- XML
- MVVM, Clean Architecture
