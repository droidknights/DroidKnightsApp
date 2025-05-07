# 🚧DroidKnights 2025 App🚧

[공식 홈페이지](https://www.droidknights.dev/)

## 개발 방향

Compose Multiplatform을 활용하여 기존 컨퍼런스 앱을 재구성 -> Compose Multiplatform 1.8.0을 체험하고, 논의하는 장이 되었으면 좋겠음

## 개발 환경
- Android Studio
  - Meerkat Feature Drop
  - Narwhal
- JDK
  - (권장) Android Studio 설치 시 Embeded 된 JDK
- Android Gradle Plugin 8.10.0
- Kotlin 2.1.20
- Compose Multiplatform 1.8.0

## Run Configuration

- composeApp (Android 에뮬레이터 or 실제 기기)
- iosApp (iOS 시뮬레이터, xcode 및 [Kotlin Multiplatform Plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform) 설치 필요)
- desktop (Windows, macOS, Linux)
- web (localhost)
- backend (localhost)

![Run Configuration](docs/image.png)

### Intel Mac에서 프로젝트 빌드하기

현재 이 프로젝트는 Apple Silicon Mac에서 iOS 앱이 빌드되도록 기본 설정되어 있습니다.

Intel Mac에서 iOS 앱을 빌드하려면, 프로젝트의 local.properties 파일에 아래 설정을 추가해 주세요:

```properties
arch=X86_64
```
