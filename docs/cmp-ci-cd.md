# DroidKnights CMP 프로젝트에 CI/CD 도입기: 멀티플랫폼 빌드 자동화 구축하기

> CMP에서 안정적인 빌드 시스템을 만들어가는 과정

DroidKnights 2025 앱 개발의 일부를 Compose Multiplatform으로 진행하기로 결정하면서, 가장 먼저 직면한 과제가 바로 빌드 환경 구축이었습니다. Android, iOS, Desktop, Web까지 총 5개 플랫폼을 지원해야 하는 상황에서, 각 플랫폼마다 서로 다른 빌드 요구사항과 환경 설정이 필요했습니다.

또한 기여자분들께서 어떤 개발 환경(Apple Silicon Mac, Intel Mac, Windows 등)을 사용하고 있을지도 모르고, 특히 로컬 환경에서는 문제없이 빌드되지만 다른 환경에서는 실패하는 경우가 빈번하게 발생하는 것이 우려되었습니다. 이런 문제를 해결하고 안정적인 개발 환경을 구축하기 위해 GitHub Actions 기반의 포괄적인 CI/CD 파이프라인을 도입하게 되었습니다.

이 글에서는 제가 DroidKnights 프로젝트에 Compose Mutliplatform CI/CD를 적용하면서 겪었던 시행착오와 최종적으로 안정화된 빌드 시스템에 대해 공유해보려고 합니다.

## 멀티플랫폼 개발의 복잡성과 도전과제

Compose(Kotlin) Multiplatform은 분명 매력적인 기술입니다. 하나의 코드베이스로 여러 플랫폼을 지원할 수 있다는 것은 개발 효율성 측면에서 큰 장점이죠. 하지만 실제로 개발을 시작하고 나니 예상치 못한 복잡함들이 기다리고 있었습니다.

**각 플랫폼별 서로 다른 빌드 환경**
- Android는 Linux 환경에서도 문제없이 빌드되지만
- iOS는 macOS + Xcode가 필수적이고
- Desktop은 Windows/macOS/Linux 각각에서 고유한 패키징 도구가 필요하며
- Web은 WebAssembly 컴파일과 JavaScript 생태계 연동이 필요했습니다

**아키텍처별 호환성 문제**

> **🍎 iOS 개발 환경의 특수성**
>
> Android 개발자에게는 생소할 수 있지만, iOS 개발은 macOS에서만 가능합니다. 더욱이 Apple이 Intel에서 자체 칩인 Apple Silicon(M1, M2 등)으로 전환하면서 개발 환경이 복잡해졌습니다.
>
> **필수 지식**
> - **Apple Silicon Mac**: ARM64 아키텍처, 최신 Xcode와 시뮬레이터 지원
> - **Intel Mac**: x86_64 아키텍처, 일부 구버전 도구와의 호환성 필요
> - **XCFramework**: iOS에서 여러 아키텍처를 하나의 프레임워크로 묶는 방식

특히 iOS 빌드가 가장 까다로웠습니다. 이젠 전부 Apple Silicon 기반 맥을 사용하시리라 기대하지만 혹시라도 Intel 기반 Mac을 사용하고 있는 분들도 고려를 안할 수 없었습니다. 따라서 CI에서도 이 상황을 고려해야 했습니다.

이런 문제들을 해결하기 위해 체계적인 CI/CD 전략을 수립하기로 했습니다.

## GitHub Actions 기본 개념과 문법

본격적인 구현 내용을 다루기 전에, GitHub Actions의 기본 문법을 먼저 살펴보겠습니다. 처음 접하시는 분들도 있을 수 있으니까요.

GitHub Actions는 YAML 형식으로 작업 흐름을 정의합니다. 기본 구조는 다음과 같습니다:

```yaml
name: 작업 흐름 이름
on:
  push:
    branches: [main]
  pull_request:
    paths-ignore: ['docs/**']

jobs:
  build:
    runs-on: ubuntu-latest
    timeout-minutes: 30
    steps:
      - name: 코드 가져오기
        uses: actions/checkout@v4
      - name: 사용자 정의 스크립트
        run: echo "Hello World"
```

여기서 중요한 개념들을 살펴보겠습니다:

- **name**: 작업 흐름의 표시명입니다. GitHub 화면에서 이 이름으로 표시됩니다
- **on**: 작업 흐름이 언제 실행될지 정의합니다. push, pull_request, schedule 등 다양한 상황을 지원합니다
- **jobs**: 실행할 작업들을 정의합니다. 여러 작업은 기본적으로 동시에 실행됩니다
- **runs-on**: 어떤 환경에서 실행할지 지정합니다. ubuntu-latest, windows-latest, macos-latest 등을 사용할 수 있습니다
- **steps**: 각 작업 내에서 순서대로 실행할 단계들입니다

**환경 변수와 상황별 정보 활용**

GitHub Actions에서는 `${{ }}` 문법으로 다양한 상황별 정보에 접근할 수 있습니다:

```yaml
env:
  GLOBAL_VAR: "전역 변수"

jobs:
  build:
    steps:
      - name: 상황별 정보 출력
        run: |
          echo "브랜치: ${{ github.ref }}"
          echo "커밋 SHA: ${{ github.sha }}"
          echo "실행 환경: ${{ runner.os }}"
          echo "이벤트 유형: ${{ github.event_name }}"
```

이런 상황별 정보들을 활용하면 브랜치별로 다른 동작을 하거나, 운영체제별로 다른 명령어를 실행하는 등 유연한 작업 흐름을 만들 수 있습니다.

**조건부 실행과 의존성 관리**

```yaml
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - name: 특정 조건에만 실행
        if: github.event_name == 'push'
        run: echo "푸시 이벤트일 때만 실행"

  deploy:
    needs: test  # test 작업이 성공했을 때만 실행
    if: github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest
    steps:
      - name: 배포 스크립트
        run: echo "메인 브랜치 배포"
```

이제 이런 기본 문법들을 알고 보시면 다음에 나올 작업 흐름들이 훨씬 이해하기 쉬우실 겁니다.

## 플랫폼별 빌드 구현 과정

### Android 빌드 구현

Android 빌드는 상대적으로 간단할 것이라고 생각했지만, CMP 환경에서는 의외로 신경 쓸 부분이 많았습니다.

```yaml
name: Android Build
on:
  push:
    branches: [2025/compose-multiplatform]
  pull_request:
    paths-ignore: ['iosApp/**', 'kotlin-js-store']

concurrency:
  group: ${{ github.workflow }}-${{ github.head_ref || github.ref }}
  cancel-in-progress: true

permissions: {}

jobs:
  build:
    permissions:
      contents: read
    runs-on: ubuntu-latest
    timeout-minutes: 60
    steps:
      - name: Checkout
        uses: actions/checkout@v4.2.2
      - uses: ./.github/actions/setup-java
      - run: ./gradlew :composeApp:assembleDebug --stacktrace
```

첫 번째로 중요하게 생각한 건 **불필요한 빌드 방지**였습니다. `paths-ignore`를 사용해서 iOS 앱 코드(`iosApp/**`)나 JavaScript 관련 파일(`kotlin-js-store`)만 변경되었을 때는 Android 빌드를 건너뛰도록 했습니다.

`concurrency` 설정도 중요했습니다. 개발하다 보면 연속으로 커밋을 올리게 되는데, 이 설정이 있으면 이전 빌드를 자동으로 취소하고 최신 빌드만 실행합니다. `github.head_ref || github.ref` 부분은 풀 리퀘스트에서는 head_ref를, 일반 푸시에서는 ref를 사용한다는 의미입니다.

이렇게 구분하는 이유는 concurrency group을 정확히 설정하기 위해서입니다. 풀 리퀘스트에서는 `github.head_ref`(소스 브랜치명)를 사용해서 같은 PR 브랜치에서 연속으로 푸시된 커밋들이 동일한 concurrency group을 가지도록 합니다. 반면 일반 푸시에서는 `github.ref`(대상 브랜치명)를 사용해서 해당 브랜치의 푸시들끼리만 서로 취소되도록 합니다. 이를 통해 서로 다른 브랜치의 빌드가 간섭하지 않으면서도, 같은 브랜치 내에서는 불필요한 중복 빌드를 방지할 수 있습니다.

`permissions: {}`로 기본 권한을 모두 제거하고, 작업 단위에서 `contents: read` 권한만 명시적으로 부여했습니다. 이렇게 CI에서 권한을 설정할 때에는 필요한 최소 권한만 주는 것이 보안상 좋습니다.

### iOS 빌드 구현

iOS 빌드에서 가장 고민이었던 건 Apple Silicon과 Intel 기반 맥에서의 빌드 안정성을 어떻게 효율적으로 관리할 것인가였습니다. 처음에는 하나의 작업 흐름에서 모든 아키텍처를 빌드하려고 했는데, 빌드 시간이 너무 오래 걸렸습니다.

**해결책: 아키텍처별 작업 흐름 분리**

Apple Silicon용 빌드:
```yaml
name: iOS Arm(Apple Silicon) Build
on:
  push:
    branches: [2025/compose-multiplatform]
  pull_request:

concurrency:
  group: ios-build-${{ github.ref }}
  cancel-in-progress: true

jobs:
  build-ios:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
      - uses: ./.github/actions/setup-java
      - name: Set up XCFramework arch filter
        run: echo "arch=ARM64" >> local.properties
      - run: make build-app-debug
        working-directory: iosApp
```

Intel용 빌드:
```yaml
name: iOS x64(Intel) Build
jobs:
  build-ios:
    runs-on: macos-13  # 명시적으로 Intel 실행 환경 사용
    steps:
      - name: Set up XCFramework arch filter
        run: echo "arch=X86_64" >> local.properties
      - run: make build-app-debug-x64
        working-directory: iosApp
```

여기서 핵심은 `runs-on` 설정입니다. `macos-latest`는 Apple Silicon 실행 환경을, `macos-13`은 Intel 실행 환경을 사용합니다. GitHub에서 제공하는 실행 환경이 다르기 때문에 이를 활용해서 아키텍처를 분리했습니다.

`working-directory: iosApp` 설정으로 특정 폴더에서 명령어를 실행할 수 있습니다. iOS 프로젝트는 별도 폴더에 있기 때문에 이 설정이 필요했습니다.

**동적 아키텍처 선택 시스템**

빌드할 때 `local.properties` 파일에 대상 아키텍처를 명시하면, 아래 코드에 의해서 기재된 아키텍처만 빌드하도록 되어있습니다:

```kotlin
internal val Project.activeArch
    get() = Arch.of(
        rootProject.layout.projectDirectory.file("local.properties").asFile.takeIf { it.exists() }
            ?.let {
                Properties().apply {
                    load(it.reader(Charsets.UTF_8))
                }.getProperty("arch")
            } ?: System.getenv("arch")
    )
```

이렇게 하니, 로컬에서는 개발자가 원하는 아키텍처를 `local.properties`에 설정할 수 있고, CI에서는 환경변수나 파일 생성으로 동적 제어가 가능해졌습니다.

> **🔧 Makefile을 사용하는 이유**
>
> iOS 빌드는 Xcode의 `xcodebuild` 명령어를 사용하는데, 이 명령어는 옵션이 매우 복잡합니다. 시뮬레이터 대상, 아키텍처, 스킴 등을 모두 지정해야 하기 때문에 Makefile로 이를 추상화했습니다.
>
> ```makefile
> build-app-debug:
> 	xcodebuild -project $(PROJECT_FILE) \
> 		-scheme $(SCHEME_NAME_APP) \
> 		-configuration Debug \
> 		-destination "platform=iOS Simulator,name=iPhone 16 Pro,OS=18.1" \
> 		clean build
> ```

### Desktop 빌드 구현

Desktop 빌드 태스크는 조금 까다로웠지만 재미있었습니다. Windows MSI, macOS DMG, Linux DEB 패키지를 모두 만들어야 했거든요.

```yaml
name: Compose Desktop(JVM) CI
jobs:
  windows:
    name: 🪟 Windows Build
    runs-on: windows-latest
    outputs:
      artifact_url: ${{ steps.upload.outputs.artifact-url }}
    steps:
      - name: Setup Java
        uses: ./.github/actions/setup-java
      - name: Package MSI
        run: ./gradlew :composeApp:packageMsi --stacktrace
      - name: Upload Windows Installer
        id: upload
        uses: actions/upload-artifact@v4
        with:
          name: desktop-msi
          path: composeApp/build/compose/binaries/main/msi/*.msi

  linux:
    name: 🐧 Linux Build
    runs-on: ubuntu-latest
    steps:
      - name: Install Debian packaging tools
        run: |
          sudo apt-get update
          sudo apt-get install -y fakeroot dpkg-dev openjdk-17-jdk
      - name: Package DEB
        run: ./gradlew :composeApp:packageDeb --stacktrace
```

여기서 `outputs`와 `id` 설정을 주목해보시기 바랍니다. `id: upload`로 단계에 고유 ID를 부여하고, `outputs`에서 이 단계의 결과를 다른 작업에서 사용할 수 있도록 노출합니다. `${{ steps.upload.outputs.artifact-url }}`로 업로드된 결과물의 URL을 참조할 수 있습니다.

> **🛠️ 플랫폼별 패키징 도구들**
>
> **jpackage**: JDK 14부터 포함된 고유 패키징 도구입니다. Java 애플리케이션을 플랫폼별 고유 설치 파일로 변환해줍니다. Compose Desktop에서 `packageMsi`, `packageDmg`, `packageDeb` 작업이 내부적으로 이 도구를 사용합니다.
>
> **fakeroot**: 일반 사용자가 루트 권한 없이도 파일 소유권을 변경하는 것처럼 보이게 해주는 도구입니다. DEB 패키지는 파일 권한 정보를 포함하기 때문에 이 도구가 필요합니다.
>
> **dpkg-dev**: 데비안 패키지 빌드 도구 모음입니다. 패키지 정보 생성, 의존성 계산, 실제 패키징을 담당합니다.

> **📦 데스크탑 배포 파일 형식들**
>
> **MSI (Microsoft Installer)**: Windows에서 사용하는 설치 패키지 형식입니다. 프로그램 추가/제거, 자동 업데이트, 롤백 등의 기능을 지원하며, Group Policy를 통한 기업 환경 배포도 가능합니다. WiX 도구로 생성됩니다.
>
> **DMG (Disk Image)**: macOS에서 사용하는 디스크 이미지 형식입니다. 애플리케이션을 드래그&드롭으로 설치할 수 있는 직관적인 설치 방식을 제공하며, 코드 사이닝과 공증(Notarization)을 통해 보안성을 보장할 수 있습니다.
>
> **DEB (Debian Package)**: 데비안과 우분투 등에서 사용하는 패키지 형식입니다. 의존성 관리, 자동 업데이트, 시스템 통합 등을 지원하며, `apt` 같은 패키지 매니저를 통해 설치할 수 있습니다.

Linux 빌드에서는 패키징 도구를 별도로 설치해야 했습니다. `sudo apt-get install -y` 명령어로 필요한 패키지들을 설치하는 것을 보실 수 있습니다.

**빌드 결과를 풀 리퀘스트에 바로 알려주기**

Desktop 빌드의 특별한 점은 빌드 결과를 풀 리퀘스트 댓글로 바로 알려준다는 것입니다:

```yaml
comment:
  name: 💬 Comment Build Status
  needs: [windows, macos, linux]
  runs-on: ubuntu-latest
  if: ${{ always() && github.event_name == 'pull_request' }}
  steps:
    - name: Post summary comment
      uses: peter-evans/create-or-update-comment@v4
      with:
        token: ${{ secrets.GITHUB_TOKEN }}
        issue-number: ${{ github.event.pull_request.number }}
        body: |
          🚀 **빌드 결과 요약**
          - 🪟 Windows: ${{ needs.windows.result == 'success' && '✅ 성공' || '❌ 실패' }}
          - 🍎 macOS: ${{ needs.macos.result == 'success' && '✅ 성공' || '❌ 실패' }}
          - 🐧 Linux: ${{ needs.linux.result == 'success' && '✅ 성공' || '❌ 실패' }}
```

`needs: [windows, macos, linux]`로 다른 작업들이 완료된 후 실행되도록 했습니다. `if: ${{ always() && github.event_name == 'pull_request' }}`에서 `always()`는 이전 작업이 실패해도 실행한다는 의미이고, `github.event_name == 'pull_request'`로 풀 리퀘스트일 때만 댓글을 달도록 제한했습니다.

`needs.windows.result`처럼 의존하는 작업의 결과 상태를 확인할 수 있습니다. 결과는 `success`, `failure`, `cancelled`, `skipped` 중 하나의 값을 가집니다.

### Web 빌드와 배포 구현

Web 빌드는 가장 실험적이면서도 흥미로운 부분이었습니다. Kotlin 코드를 WebAssembly로 컴파일해서 웹 브라우저에서 실행할 수 있게 만드는 과정은 기존 웹 개발과는 완전히 다른 접근 방식이었습니다.

> **🌐 WebAssembly와 Kotlin/Wasm의 동작 원리**
>
> **WebAssembly(WASM)**는 웹 브라우저에서 거의 네이티브 수준의 성능으로 코드를 실행할 수 있게 해주는 이진 명령어 형식입니다. JavaScript의 한계를 뛰어넘는 성능을 제공하면서도 브라우저의 보안 모델 안에서 안전하게 실행됩니다.
>
> **Kotlin/Wasm**은 Kotlin 코드를 WASM 바이트코드로 컴파일해서, 기존에 JavaScript로만 가능했던 웹 개발을 Kotlin으로 할 수 있게 해줍니다. 특히 Compose Multiplatform과 결합되면 Android에서 사용하던 UI 코드를 그대로 웹에서도 사용할 수 있습니다.

**WASM 빌드 과정 상세 분석**

```yaml
name: Compose Wasm(Web) Build
on:
  push:
    branches: [2025/compose-multiplatform]
  pull_request:
    paths-ignore: ['iosApp/**']

jobs:
  build:
    runs-on: ubuntu-latest
    timeout-minutes: 60
    steps:
      - name: Checkout
        uses: actions/checkout@v4
      - uses: ./.github/actions/setup-java
      - name: kotlinUpgradeYarnLock
        run: ./gradlew kotlinUpgradeYarnLock
      - run: ./gradlew :composeApp:buildWebApp --stacktrace
```

**buildWebApp 작업의 내부 동작**

실제 프로젝트에서 `buildWebApp` 작업은 다음과 같이 구현되어 있습니다:

```kotlin
val buildWebApp by tasks.registering(Copy::class) {
    val wasmDist = "wasmJsBrowserDistribution"

    from(tasks.named(wasmDist).get().outputs.files)

    into(layout.buildDirectory.dir("webApp"))

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
```

이 구현은 [KotlinConf App](https://github.com/JetBrains/kotlinconf-app)을 참조한 것으로, 다음과 같은 과정을 거칩니다:

1. **wasmJsBrowserDistribution 태스크 실행**: Kotlin/Wasm 컴파일러가 Kotlin 소스 코드를 WASM 바이트코드로 컴파일하고, 웹 브라우저에서 실행 가능한 형태로 패키징합니다.

2. **JavaScript 브리지 생성**: WASM 모듈과 JavaScript 사이의 상호 작용을 위한 브리지 코드를 자동 생성합니다. DOM 조작, 브라우저 API 호출 등이 이를 통해 이뤄집니다.

3. **정적 자원 처리**: 이미지, 폰트, CSS 등의 정적 자원들을 웹 앱 구조에 맞게 복사하고 최적화합니다.

4. **HTML 생성**: WASM 모듈을 로드하고 초기화하는 HTML 파일을 생성합니다. 이 HTML은 WASM 로딩과 JavaScript 브리지 초기화를 담당합니다.

5. **배포 가능한 형태로 복사**: `Copy` 태스크를 통해 모든 필요한 파일들을 `build/webApp` 디렉토리로 복사하여 배포 준비를 완료합니다.

**JavaScript 의존성 관리**

Kotlin/Wasm 프로젝트에서도 여전히 JavaScript 생태계와의 연동이 필요합니다. 브라우저 API 바인딩, UI 컴포넌트 라이브러리 등을 사용하기 때문입니다.

```yaml
- name: kotlinUpgradeYarnLock
  run: ./gradlew kotlinUpgradeYarnLock
```

이 단계는 JavaScript 의존성들의 버전을 최신 상태로 업데이트하고 `yarn.lock` 파일을 갱신합니다. 이를 통해 빌드 환경 간 의존성 버전 불일치 문제를 방지하고, 보안 업데이트가 적용된 최신 라이브러리를 사용할 수 있습니다.

**WASM 빌드의 특수한 고려사항**

- **메모리 관리**: WASM의 선형 메모리 모델에 맞춰 Kotlin의 객체 할당과 가비지 컬렉션이 조정됩니다
- **브라우저 호환성**: 다양한 브라우저에서의 WASM 지원 수준을 고려해야 합니다
- **번들 크기**: WASM 바이너리와 JavaScript 브리지의 크기를 최적화해야 합니다
- **로딩 성능**: WASM 모듈의 컴파일과 인스턴스화 시간을 고려한 로딩 전략이 필요합니다

**GitHub Pages 자동 배포 구현**

웹 앱을 빌드하는 것에서 한 걸음 더 나아가, GitHub Pages를 통해 자동으로 배포까지 이뤄지도록 구현했습니다:

```yaml
name: Publish Wasm App on GitHub Pages
on:
  push:
    branches: [2025/compose-multiplatform]
  workflow_dispatch:

jobs:
  build:
    name: Build Kotlin/Wasm
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4
      - uses: ./.github/actions/setup-java
      - name: Run Gradle Tasks
        run: ./gradlew :composeApp:buildWebApp
      - name: Fix permissions
        run: |
          chmod -v -R +rX "composeApp/build/webApp/" | while read line; do
            echo "::warning title=Invalid file permissions automatically fixed::$line"
          done
      - name: Upload Pages artifact
        uses: actions/upload-pages-artifact@v3
        with:
          path: composeApp/build/webApp/

  deploy:
    needs: build
    permissions:
      contents: read
      pages: write
      id-token: write
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    runs-on: ubuntu-latest
    steps:
      - name: Debug Permissions
        run: env | grep ACTIONS_ID_TOKEN_REQUEST_URL || echo "ACTIONS_ID_TOKEN_REQUEST_URL not set"
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

**GitHub Pages 배포의 핵심 개념들**

GitHub Pages 배포는 일반적인 파일 업로드와는 다른 특별한 과정을 거칩니다:

1. **빌드와 배포 분리**: `build`와 `deploy` 작업을 분리해서, 빌드가 성공한 경우에만 배포가 진행되도록 했습니다.

2. **파일 권한 처리**: `chmod -v -R +rX` 명령으로 모든 파일에 읽기 및 실행 권한을 부여합니다. GitHub Pages는 특정 권한이 설정된 파일만 제대로 서비스할 수 있기 때문입니다.

3. **특별한 권한 설정**: GitHub Pages 배포에는 일반적인 저장소 권한 외에 추가 권한이 필요합니다:
   - `pages: write`: Pages 사이트에 배포할 수 있는 권한
   - `id-token: write`: 안전한 배포를 위한 임시 토큰 사용 권한

4. **환경 설정**: `environment` 블록으로 배포 환경을 정의합니다. 이를 통해 배포 기록을 추적하고, 필요한 경우 배포 승인 과정을 추가할 수도 있습니다.

5. **수동 배포 지원**: `workflow_dispatch` 이벤트를 추가해서 GitHub 웹 인터페이스에서 수동으로도 배포를 실행할 수 있도록 했습니다.

`echo "::warning title=..."` 구문은 GitHub Actions의 특별한 문법입니다. 작업 흐름 로그에 경고 메시지를 출력할 수 있습니다. `::error`, `::notice` 등도 사용할 수 있습니다.

**배포 결과 확인과 디버깅**

배포가 완료되면 `steps.deployment.outputs.page_url`을 통해 배포된 사이트의 URL을 얻을 수 있습니다. 이를 활용해서 배포 완료 알림을 보내거나, 자동화된 테스트를 실행할 수도 있습니다.

만약 배포 과정에서 문제가 발생하면, 환경 변수를 확인해볼 수 있는 디버깅 단계를 포함시켰습니다. 특히 권한 관련 설정이 제대로 되어있는지 확인하는 것이 중요합니다.

## 빌드 성능 최적화 전략

처음에는 빌드 시간이 정말 오래 걸렸습니다. 전체 빌드를 실행하면 20-30분씩 소요되어서 답답했거든요. 이를 개선하기 위해 여러 최적화 작업을 진행했습니다.

### Gradle 설정 최적화: 환경별 맞춤 설정

로컬 개발 환경과 CI 환경의 특성이 다르기 때문에, 각각에 최적화된 설정을 따로 관리했습니다.

**로컬 개발 환경(`gradle.properties`)**
```properties
# 개발자 편의성과 반복 빌드 최적화
org.gradle.daemon=true
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true
kotlin.incremental=true
kotlin.daemon.jvmargs=-Xmx4096M
kotlin.native.ignoreDisabledTargets=true
```

**CI 환경(`.github/ci-gradle.properties`)**
```properties
# 안정성과 자원 효율성 중심
org.gradle.daemon=false
org.gradle.jvmargs=-Xmx5120m
org.gradle.parallel=true
org.gradle.workers.max=2
kotlin.incremental=false
kotlin.compiler.execution.strategy=in-process
```

**핵심 성능 설정들 상세 분석**

**메모리 관리 설정**
- `org.gradle.jvmargs`: Gradle의 JVM 힙 메모리를 설정합니다. KMP 프로젝트에서는 여러 플랫폼을 동시에 컴파일하므로 충분한 메모리가 필요합니다
- `XX:MaxMetaspaceSize`: 클래스 메타데이터 저장 공간을 제한해서 메모리 누수를 방지합니다
- `XX:+HeapDumpOnOutOfMemoryError`: 메모리 부족 시 힙 덤프를 생성해서 문제 분석을 도와줍니다
- `kotlin.daemon.jvmargs`: Kotlin 컴파일러 데몬의 메모리를 별도로 설정합니다

**병렬 처리 설정**
- `org.gradle.parallel=true`: 독립적인 프로젝트들을 병렬로 빌드해서 전체 빌드 시간을 단축시킵니다
- `org.gradle.workers.max`: 동시에 실행할 수 있는 작업자 스레드 수를 제한합니다. CI 환경에서는 자원 제약을 고려해 낮게 설정합니다

**빌드 최적화 설정**
- `org.gradle.caching=true`: 이전 빌드 결과를 저장해서 변경되지 않은 부분은 재사용합니다
- `org.gradle.configureondemand=true`: 실제 필요한 프로젝트만 구성해서 초기 설정 시간을 단축시킵니다
- `kotlin.incremental=true`: 변경된 파일만 다시 컴파일하는 증분 컴파일을 활성화합니다

**Kotlin 특화 설정**
- `kotlin.compiler.execution.strategy=in-process`: 컴파일러를 별도 프로세스가 아닌 Gradle 프로세스 내에서 실행해서 오버헤드를 줄입니다
- `kotlin.native.ignoreDisabledTargets=true`: 현재 환경에서 빌드할 수 없는 네이티브 타겟을 무시해서 오류를 방지합니다

**환경별 최적화 전략**

로컬 개발 환경에서는 **개발자 생산성**에 초점을 맞췄습니다. Gradle 데몬을 활성화해서 JVM 시작 시간을 절약하고, 증분 컴파일로 변경된 부분만 다시 빌드합니다. `configureondemand` 옵션으로 대규모 멀티모듈 프로젝트에서 필요한 모듈만 구성해서 초기 설정 시간을 단축시킵니다.

CI 환경에서는 **안정성과 자원 효율성**을 우선했습니다. 매번 깨끗한 환경에서 빌드하므로 데몬이나 증분 컴파일의 이점이 적고, 오히려 메모리 사용량만 늘어납니다. 대신 힙 메모리를 더 할당하고 작업자 수를 제한해서 자원 제약 상황에서도 안정적으로 빌드할 수 있도록 했습니다.

### 저장소 전략: 똑똑한 재사용 시스템

가장 큰 성능 향상은 저장소에서 나왔습니다. 여러 층의 저장소를 구축해서 빌드 시간을 대폭 단축시킬 수 있었습니다:

```yaml
- name: Cache Gradle dependencies & build outputs
  uses: actions/cache@v4
  with:
    path: |
      ~/.gradle/caches
      ~/.gradle/wrapper
      .gradle
    key: |
      ${{ runner.os }}-gradle-\
      ${{ hashFiles('**/*.gradle*','**/gradle-wrapper.properties','gradle/libs.versions.toml') }}
    restore-keys: |
      ${{ runner.os }}-gradle-

- name: Set Konan Cache Key
  id: konan-cache-key
  shell: bash
  run: echo "KOTLIN_VERSION=$(grep -oE 'kotlin\s*=\s*"[0-9.]*"' gradle/libs.versions.toml | grep -oE '[0-9.]+')" >> $GITHUB_OUTPUT

- name: Cache Konan
  uses: actions/cache@v4
  with:
    path: ~/.konan
    key: v1-konan-${{ runner.os }}-${{ steps.konan-cache-key.outputs.KOTLIN_VERSION }}

- name: Cache WiX Toolset
  uses: actions/cache@v4
  with:
    path: ~/.gradle/caches/modules-2/files-2.1/org.jetbrains.compose/native-wix
    key: wix-${{ runner.os }}-v3.11.2
```

**저장소 전략 상세 분석**

**Gradle 의존성 저장소**
가장 기본이 되는 저장소입니다. `~/.gradle/caches`에는 다운로드된 라이브러리들이, `~/.gradle/wrapper`에는 Gradle 배포판이 저장됩니다. 프로젝트 루트의 `.gradle` 폴더에는 빌드 메타데이터와 컴파일 결과물이 저장됩니다.

저장소 키는 `hashFiles()` 함수로 생성됩니다. 이 함수는 빌드 스크립트(`**/*.gradle*`), Gradle 래퍼 설정(`gradle-wrapper.properties`), 버전 카탈로그(`gradle/libs.versions.toml`)의 해시값을 계산합니다. 이 파일들 중 하나라도 변경되면 새로운 저장소가 생성되고, 변경되지 않았다면 기존 저장소를 재사용합니다.

**Konan 네이티브 라이브러리 저장소**
Kotlin/Native 컴파일러인 Konan이 사용하는 네이티브 라이브러리들을 저장합니다. iOS나 Desktop 빌드에서 C/C++ 라이브러리를 사용할 때 이들을 다운로드하고 컴파일하는 과정이 매우 오래 걸리므로, 이를 저장해두는 것이 중요합니다.

Kotlin 버전이 바뀌면 Konan의 내부 구조도 변경될 수 있으므로, Kotlin 버전을 저장소 키에 포함시켰습니다. 동적으로 `gradle/libs.versions.toml`에서 Kotlin 버전을 추출해서 저장소 키로 사용합니다.

**WiX 도구 저장소**
Windows Desktop 애플리케이션의 MSI 패키지를 생성하는 WiX 도구는 크기가 크고 다운로드가 오래 걸립니다. 이를 별도로 저장해두면 Windows 빌드 시간을 크게 단축할 수 있습니다.

**저장소 복원 전략**

`restore-keys` 옵션은 정확한 저장소가 없을 때 부분 일치로 저장소를 복원하는 기능입니다. 예를 들어 의존성이 조금 변경되어서 정확한 저장소가 없더라도, 운영체제가 같은 이전 저장소를 찾아서 기본으로 사용하고 변경된 부분만 다시 다운로드합니다.

이런 다층 저장소 전략을 통해 초기 빌드는 20-30분이 걸렸지만, 저장소가 활성화된 후에는 5-10분으로 빌드 시간을 단축할 수 있었습니다.

## 빌드 설정의 일관성 보장

멀티플랫폼 프로젝트에서 각 모듈마다 일관된 빌드 설정을 유지하는 것은 정말 중요합니다. 특히 CI/CD 환경에서는 모든 모듈이 동일한 설정으로 빌드되어야 예측 가능한 결과를 얻을 수 있습니다.

### 공통 Java 설정 동작 만들기

제일 먼저 만든 것은 공통 설정을 처리하는 재사용 가능한 동작이었습니다:

```yaml
# .github/actions/setup-java/action.yml
name: "Setup JDK"
description: "Setup JDK and Gradle, Konan & WiX caching"

runs:
  using: "composite"
  steps:
    - name: Copy CI gradle.properties
      shell: bash
      run: |
        mkdir -p ~/.gradle
        cp .github/ci-gradle.properties ~/.gradle/gradle.properties

    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        distribution: 'temurin'
        java-version: '17'

    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@v4
      with:
        gradle-version: wrapper

    - name: Cache WiX Toolset
      uses: actions/cache@v4
      with:
        path: ~/.gradle/caches/modules-2/files-2.1/org.jetbrains.compose/native-wix
        key: wix-${{ runner.os }}-v3.11.2
```

`using: "composite"`는 여러 단계를 조합해서 하나의 동작으로 만드는 방식입니다. 이렇게 하니까 모든 작업 흐름에서 `uses: ./.github/actions/setup-java` 한 줄로 일관된 환경 설정이 가능해졌습니다.

각 단계에서 `shell: bash`를 명시한 것도 중요합니다. 조합형 동작에서는 셸을 명시적으로 지정해야 합니다.

## 실제 운영하면서 마주한 문제들과 해결 과정

### 빌드 실패는 빨리, 성공은 확실하게

초기에는 모든 플랫폼을 동시에 빌드하다가 하나만 실패해도 전체가 실패하는 문제가 있었습니다. 지금은 각 플랫폼을 독립적으로 빌드하면서, 실패한 플랫폼만 다시 실행할 수 있게 했습니다.

### iOS 빌드의 함정들

iOS 빌드에서 가장 많이 마주친 문제는 Xcode 버전 호환성이었습니다. `macos-latest`와 `macos-13` 실행환경의 Xcode 버전이 다르니까, 빌드 스크립트에서 시뮬레이터 버전도 맞춰줘야 했습니다:

```makefile
# Apple Silicon (최신 시뮬레이터)
-destination "platform=iOS Simulator,name=iPhone 16 Pro,OS=18.1"

# Intel (구버전 시뮬레이터)  
-destination "platform=iOS Simulator,name=iPhone 15 Pro,OS=17.2"
```

> **📱 iOS 시뮬레이터 버전 관리**
>
> Xcode 버전에 따라 지원하는 iOS 시뮬레이터 버전이 다릅니다. GitHub의 `macos-latest` 실행환경은 최신 Xcode를, `macos-13`은 구버전 Xcode를 사용합니다. 따라서 각각 다른 시뮬레이터 버전을 지정해야 합니다.
>
> 또한 기기명도 중요합니다. 구버전 Xcode에서는 iPhone 16 Pro를 지원하지 않을 수 있으므로, 해당 Xcode 버전에서 사용 가능한 기기를 지정해야 합니다.

이런 세부사항들을 놓치면 빌드가 실패합니다.

### 메모리 관리의 중요성

CI 환경에서는 메모리 제약이 생각보다 까다롭습니다. 특히 KMP 프로젝트에서는 여러 플랫폼을 동시에 컴파일하다 보니까 OutOfMemoryError가 자주 발생했습니다.

해결책은 **작업자 수 제한**과 **적절한 힙 메모리 할당**이었습니다:

```properties
org.gradle.jvmargs=-Xmx5120m
org.gradle.workers.max=2
```

이런 설정들을 하나하나 찾아가면서 조정하는 과정도 의미있었습니다.

## 마무리

이와 같이 CI/CD를 구축하는 것은 복잡하지만, 한 번 제대로 설정해두면 프로젝트에 참가한 기여자 전체의 개발 생산성을 크게 향상시킬 수 있습니다. 특히 각 플랫폼별 호환성 문제를 조기에 발견하고, 일관된 빌드 환경을 보장할 수 있다는 점에서 그 가치가 큽니다.

이번 CI/CD 기여를 통해 얻은 것은 단순히 기술적인 구현 방법뿐만 아니라, 각 플랫폼의 특성을 이해하고 그에 맞는 최적화 전략을 수립하는 것의 중요성이었습니다.

DroidKnights 프로젝트의 CI/CD 설정은 [GitHub 저장소](https://github.com/droidknights/DroidKnightsApp/tree/2025/compose-multiplatform/.github/workflows)에서 확인하실 수 있습니다. 여러분의 CMP 개발 여정에 도움이 되었으면 좋겠습니다.

---

*이 글에서 소개한 CI/CD 시스템은 실제 DroidKnights 2025 앱 개발에 적용되어 운영 중입니다. 더 자세한 내용이나 질문이 있으시면 언제든 연락주세요! 📧*