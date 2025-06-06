package com.droidknights.app.core.network.mock

internal const val SESSIONS_PATH = "/droidknights/DroidKnightsApp/refs/heads/2025/app/assets/sessions/sessions.json"

internal val SESSIONS_JSON = """
    [
      {
        "id": "1",
        "title": "키노트",
        "content": "",
        "speakers": [],
        "tags": [],
        "room": null,
        "startTime": "2025-06-17T10:40:00.000",
        "endTime": "2025-06-17T11:00:00.000"
      },
      {
        "id": "2",
        "title": "카카오뱅크 Compose 적용기",
        "content": "2024년 4월부터 2025년 2월까지 카카오뱅크 안드로이드 앱에 Compose를 도입했습니다. 전략과 방향성을 공유하고 약 10개월간의 여정이 신기술 도입을 앞두고 있는 분들에게 작은 이정표가 되기를 희망합니다.",
        "speakers": [
          {
            "name": "이수호",
            "introduction": "카카오뱅크에서 공유, 시너지, 즐거움의 가치를 갖고 안드로이드 리딩 및 개발을 진행하고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/이수호.jpeg"
          }
        ],
        "tags": [
          "Compose"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T11:00:00.000",
        "endTime": "2025-06-17T11:30:00.000"
      },
      {
        "id": "3",
        "title": "KMP로 Figma 아이콘 동기화, 클릭 한 번이면 끝!",
        "content": "개발자와 디자이너 협업에 Figma를 많이 활용하는데요, 밴드 팀에서는 Figma API로 리소스 관리를 자동화한 사례가 있습니다. 이러한 과정을 소개하고 여러분께 노하우를 공유하고자 합니다.",
        "speakers": [
          {
            "name": "옥수환",
            "introduction": "네이버 밴드 안드로이드 팀에서 KMP와 자동화로 개발 효율을 고민하는 개발자입니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/옥수환.jpeg"
          }
        ],
        "tags": [
          "KMP"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T11:50:00.000",
        "endTime": "2025-06-17T12:35:00.000"
      },
      {
        "id": "4",
        "title": "전방위 자동화 시대, CI/CD를 넘어 PM과 QA까지 확장하기",
        "content": "개발과 배포의 경계를 넘어 기획부터 QA까지, Jenkins와 GitHub Actions 기반의 CI/CD로 시작하여 Webhook, ClickUp, Jira, Slack Block-kit, Appium을 활용한 다방면의 업무 자동화를 구축해 팀의 업무 능률 개선과 함께 커뮤니케이션 비용까지 절감한 경험을 나누고자 합니다.",
        "speakers": [
          {
            "name": "김호중",
            "introduction": "십여 년 전, 처음 직접 만든 모바일 앱을 빌드하던 순간 새로운 세상을 만났습니다. 일상의 불편함을 기술로 해결하는 것을 좋아하며, 오늘도 누군가의 문제를 풀기 위해 살아갑니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/김호중.jpeg"
          }
        ],
        "tags": [
          "자동화",
          "생산성"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T13:55:00.000",
        "endTime": "2025-06-17T14:25:00.000"
      },
      {
        "id": "5",
        "title": "Android에서 실현 가능한 모든 AI",
        "content": "지금이라도 늦지 않았습니다! Android에서 AI를 어떻게 적용할 수 있는가에 대해서 Google이 제공하는 모든 AI 솔루션들을 살펴봅니다.\n- 앱에서 생성형 AI를 굳이 써야하나?\n- Gemini Nano / Gemma의 동작 원리\n  * Gemma: LoRA와 ICL을 이용한 tuning 전략\n- GenAI API: 뭘 좋아하실지 몰라 다 준비해봤습니다\n- LiteRT: 전통적인 기계학습을 적용하고 최적화 하기\n- Vertex AI: 더 복잡한 생성형 AI가 필요하다면?",
        "speakers": [
          {
            "name": "강사룡",
            "introduction": "Senior Developer Relations Engineer @ Google\n\n현재 Android Developer Relations 부서에서 Top tier Android 앱 개발사들과의 개발 협업을 담당하고 있습니다. 20% 프로젝트로 On-device AI 개발에도 참여하고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/강사룡.jpg"
          }
        ],
        "tags": [
          "Al"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T14:45:00.000",
        "endTime": "2025-06-17T15:15:00.000"
      },
      {
        "id": "6",
        "title": "당신의 클린아키텍처는 틀렸다",
        "content": "안드로이드 개발자라면, '클린 아키텍처'라는 말을 모두 들어보셨을겁니다.\n\n그래서 많은 개발자들이 '클린 아키텍처'로 앱을 개발하고 있다고 말합니다.\n\n하지만 진짜 클린 아키텍처 구조로 앱을 만들고 있는 사람은 많지 않습니다.\n\n'클린 아키텍처'라고 말하는 사람들의 실제 프로젝트는 '클린 아키텍처'가 아니기 때문입니다.\n\n어느정도 눈치 채셨겠지만\nGoogle의 '앱 아키텍처'와 '클린 아키텍처'는 완전히 다른 설계입니다.\n\n이 발표를 통해 각각의 아키텍처가 추구하는 방향과 차이점을 이해하고 본인에게 적합한 아키텍처를 선택할 수 있게 될겁니다.\n\n'클린 아키텍처'라는 것이 왜 중요하고 어떤 점을 가장 중요하게 생각해야 하는지에 대해서도 이야기합니다.",
        "speakers": [
          {
            "name": "박상권",
            "introduction": "현) 헤이딜러 안드로이드팀 리더\n\n저는 사람들이 비효율과 불합리를 당연히 여기지 않도록 돕고, 더 나은 선택과 환경을 만들어갈 수 있도록 노력합니다.\n\n이를 통해 공정하게 대우받으며, 좋은 것을 공유하고 함께 누리는 세상을 꿈꿉니다.\n\n개발, 강의, 블로그, 오픈소스, 조직문화 개선등 제가 하는 모든 일은 이러한 세상을 만드는 데 기여하기 위한 실천입니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/박상권.jpeg"
          }
        ],
        "tags": [
          "아키텍처"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T15:35:00.000",
        "endTime": "2025-06-17T16:20:00.000"
      },
      {
        "id": "7",
        "title": "다양한 스크롤 뷰에서의 영상 재생",
        "content": "HorizontalPager, RecyclerView, 디바이스 화면을 넘어가는 Column 등 다양한 스크롤 상황이 존재합니다. 이렇게 다양한 UI 환경 속에서 어떻게 영상 로직을 대응할 수 있을까요? 각 상황별로 발생하는 문제를 그룹화하고, 영상 재생 과정에서 마주쳤던 이슈들을 어떻게 해결했는지 논의하고자 합니다.",
        "speakers": [
          {
            "name": "이가은",
            "introduction": "라텔앤드파트너즈에서 신규 사업을 담당하는 안드로이드 신입 개발자입니다. 문제 해결 과정을 즐기며, 그 과정을 통해 얻은 경험을 여러분과 나누고자 합니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/이가은.jpg"
          }
        ],
        "tags": [
          "성능"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T16:40:00.000",
        "endTime": "2025-06-17T17:10:00.000"
      },
      {
        "id": "8",
        "title": "레드팀 화이트해커의 Offensive 관점 안드로이드 앱 보안(feat. CVE-2025-****)",
        "content": "레드팀 화이트해커의 공격적인 관점으로 안드로이드 앱의 주요 취약점과 실전 모바일 침투 사례를 분석하고, 안전한 앱 개발을 위한 시큐어코딩 및 대응 방안을 공유합니다.",
        "speakers": [
          {
            "name": "김주원",
            "introduction": "웹 해킹, 모바일 해킹, 내부침투, 사이버 무기에 관심을 가지고 연구하고 있으며 해킹 대회(CTF)와 버그바운티(취약점 제보) 플레이를 즐겨하고 있습니다!",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/김주원.jpeg"
          }
        ],
        "tags": [
          "화이트해커",
          "보안"
        ],
        "room": "Track1",
        "startTime": "2025-06-17T17:30:00.000",
        "endTime": "2025-06-17T18:00:00.000"
      },
      {
        "id": "9",
        "title": "ReadMoreTextView: 텍스트 \"더보기\" 기능 구현하기",
        "content": "긴 텍스트를 자연스럽게 접고 펼 수 있게!\nAndroid View와 Jetpack Compose에서 텍스트 \"더보기\" 기능을 구현하는 방법을 살펴봅니다.\n웹툰에서 직접 만들고 안드로이드 앱에도 사용하고 있는 ReadMore TextView 라이브러리를 기반으로 설명합니다.",
        "speakers": [
          {
            "name": "안성용",
            "introduction": "네이버웹툰에서 글로벌웹툰 앱을 만들고 있는 안드로이드 개발자입니다.\nAndroid GDE로도 활동하고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/안성용.jpg"
          }
        ],
        "tags": [
          "Compose"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T11:00:00.000",
        "endTime": "2025-06-17T11:30:00.000"
      },
      {
        "id": "10",
        "title": "Benchmark와 Baseline Profile을 사용해 LazyColumn 스크롤 성능을 75% 개선하기까지의 여정",
        "content": "Compose 성능 최적화 요소는 무엇이 있을까요?\n\nStable 한 함수 설계부터 시작해, Frame Rendering 3단계를 염두한 Composition 건너뛰기, 이미지 로딩 방식의 이해 등, 여러 가지가 있습니다!\n\n제가 만났던 문제들을 Benchmark, Baseline Profile, Perfetto, Layout Inspector를 활용하며 진단해 나가고, 해결한 저의 여정을 공유합니다!",
        "speakers": [
          {
            "name": "송상윤",
            "introduction": "펜타시큐리티에 재직중인 5년차 개발자 송상윤입니다. 기존 레거시 코드들을 안전한 방식으로 개선하는 업무를 가장 좋아합니다. 또한 스스로 연구한 자료들을 글로 정리하고 개인 프로젝트에 적용하는 재미를 느끼고 있습니다!",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/송상윤.jpg"
          }
        ],
        "tags": [
          "성능"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T11:50:00.000",
        "endTime": "2025-06-17T12:35:00.000"
      },
      {
        "id": "11",
        "title": "제미나이와 함께 안드로이드 공부하기",
        "content": "AI 시대 안드로이드 개발자에게 더 많은 역할과 책임이 주어집니다. 주니어 개발자로서 어떻게 대비할 수 있을까요? AI를 활용해 학습의 효율을 높일 수 있다면 하나의 방법이 될 수 있을까요? 제미나이를 통해 더 넓고 깊게 안드로이드를 이해하고, 개발자로서 성장할 수 있는 방법에 대해 고민한 내용을 공유하고 함께 이야기 나누고자 합니다.",
        "speakers": [
          {
            "name": "양찬석",
            "introduction": "10년 간 구글 안드로이드 DevRel 팀에서 개발자분들을 돕는 일을 하고 있습니다. 최근에는 AI 시대에 개발자 관계팀의 미래를 고민하며, 개발자 문화와 교육에 대해 다시 한번 생각해보고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/양찬석.jpg"
          }
        ],
        "tags": [
          "Al"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T13:55:00.000",
        "endTime": "2025-06-17T14:25:00.000"
      },
      {
        "id": "12",
        "title": "Jetpack XR 살펴보기: XR 개발은 어떻게 이루어지는가?",
        "content": "Android SDK 발표와 함께 Jetpack XR 라이브러리가 공개 되었습니다.\n\n가이드 상 라이브러리만 추가하면 Android XR을 개발 할 수 있다고 합니다.\n\n어떻게 모바일과 동일한 Android SDK만 가지고 XR 기능을 라이브러리만으로 제공할 수 있을까요?\n\n이에 대해 알아보기 위해서 함께 Jetpack XR 라이브러리를 살펴 봅니다.",
        "speakers": [
          {
            "name": "윤희성",
            "introduction": "삼성전자 MX사업부에서 UI 라이브러리를 개발 하고 있습니다.\n프로젝트 무한에 참여 하면서 Jetpack XR 라이브러리를 앞서 살펴볼 수 있는 기회를 얻게 되어 그 동안 분석한 내용을 공유하고자 합니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/윤희성.jpeg"
          }
        ],
        "tags": [
          "XR"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T14:45:00.000",
        "endTime": "2025-06-17T15:15:00.000"
      },
      {
        "id": "13",
        "title": "컴포즈 스냅샷 내부원리 찍어먹기",
        "content": "이제는 안드로이드 개발자와 항상 함께하는 Compose, 그 내부 원리는 얼마나 이해하고 계신가요? Jetpack Compose의 내부 원리 중 하나인 Snapshot을 찍어 먹어봅니다.",
        "speakers": [
          {
            "name": "강다현",
            "introduction": "쉬운 코드를 지향하면서도, 내부 구조의 깊이 있는 이해를 좋아하는 개발자입니다. 현재 채널톡에서 안드로이드 개발자로 일하고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/강다현.jpeg"
          }
        ],
        "tags": [
          "Compose"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T15:35:00.000",
        "endTime": "2025-06-17T16:20:00.000"
      },
      {
        "id": "14",
        "title": "나도 edgeToEdge 적용하기 싫어. 근데 누군가는 해야 하잖아?",
        "content": "이번 발표에서는 SDK35 이상을 타겟팅하는 앱에 Edge-to-Edge 디자인을 적용하는 방법에 대해 이야기하려 합니다. SOOP 앱에서 직접 구현하면서 겪었던 고민과 문제점을 공유할 예정입니다. 실제 적용 경험을 바탕으로 여러분들이 자신의 앱에 Edge-to-Edge를 더 쉽고 효과적으로 구현하는 데 도움이 되었으면 합니다.",
        "speakers": [
          {
            "name": "이지훈",
            "introduction": "작년 GDG super.init v5에서 Custom Lint 개발일지를 발표했던 이지훈입니다. 현재 SOOP 이라는 스트리밍 회사에서 Android 개발자로 일하고 있으며, 이번에는 Edge-to-Edge 적용 경험을 공유하고자 합니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/이지훈.jpg"
          }
        ],
        "tags": [
          "EdgeToEdge"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T16:40:00.000",
        "endTime": "2025-06-17T17:10:00.000"
      },
      {
        "id": "15",
        "title": "Bytecode Manipulation을 통해 생산성 높이기",
        "content": "Bytecode Manipulation 개념과 동작 원리를 ASM 예제를 통해 쉽게 설명합니다. 반복 작업 자동화와 중복 코드 제거 등 개발 생산성 향상 관점으로 알아보고, 실전 적용 팁을 나눕니다.",
        "speakers": [
          {
            "name": "강대규",
            "introduction": "다양한 서비스를 만들며, 기술보다 그 너머에 있는 맥락과 사람에 더 많이 집중하고 있습니다.",
            "imageUrl": "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/강대규.png"
          }
        ],
        "tags": [
          "ASM",
          "생산성"
        ],
        "room": "Track2",
        "startTime": "2025-06-17T17:30:00.000",
        "endTime": "2025-06-17T18:00:00.000"
      }
    ]
""".trimIndent()
