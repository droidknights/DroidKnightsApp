package com.droidknights.app.feature.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.CircularProgressIndicator
import com.droidknights.app.core.designsystem.components.HorizontalDivider
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import com.droidknights.app.feature.session.components.SessionDetailBookmarkStatePopup
import com.droidknights.app.feature.session.components.SessionDetailChips
import com.droidknights.app.feature.session.components.SessionDetailSpeaker
import com.droidknights.app.feature.session.components.SessionDetailTopAppBar
import com.droidknights.app.feature.session.components.SessionOverview
import com.droidknights.app.feature.session.model.SessionDetailEffect
import com.droidknights.app.feature.session.model.SessionDetailUiState
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    modifier: Modifier = Modifier,
    viewModel: SessionDetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showPopup by remember { mutableStateOf(false) }
    var lastBookmarkState by remember { mutableStateOf(false) }

    LaunchedEffect(sessionId) {
        viewModel.fetchSession(sessionId)
    }

    LaunchedEffect(viewModel) {
        viewModel.sessionUiEffect.collect { effect ->
            if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
                lastBookmarkState = effect.bookmarked
                showPopup = true
                delay(1000L)
                showPopup = false
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Surface(
            color = KnightsTheme.colorScheme.background,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                SessionDetailTopAppBar(
                    bookmarked = (uiState as? SessionDetailUiState.Success)?.bookmarked == true,
                    onClickBookmark = { viewModel.toggleBookmark() },
                    onBackClick = viewModel::navigateBack,
                )

                when (val state = uiState) {
                    is SessionDetailUiState.Loading -> SessionDetailLoading()
                    is SessionDetailUiState.Success -> SessionDetailContent(state.session)
                }
            }
        }

        SessionDetailBookmarkStatePopup(
            visible = showPopup,
            bookmarked = lastBookmarkState,
        )
    }
}

@Composable
private fun SessionDetailLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun SessionDetailContent(session: Session) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = session.title,
            style = KnightsTheme.typography.headlineMediumB,
        )

        Spacer(modifier = Modifier.height(12.dp))

        SessionDetailChips(session = session)

        if (session.content.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SessionOverview(content = session.content)
        }

        Spacer(modifier = Modifier.height(40.dp))

        HorizontalDivider(thickness = 1.dp, color = KnightsTheme.colorScheme.borderColor)

        Spacer(modifier = Modifier.height(40.dp))

        Column(verticalArrangement = Arrangement.spacedBy(40.dp)) {
            session.speakers.forEach { speaker ->
                SessionDetailSpeaker(speaker)
            }
        }
    }
}

@Preview()
@Composable
private fun SessionDetailScreenPreview() {
    val sampleSession = Session(
        id = "6",
        title = "당신의 클린아키텍처는 틀렸다",
        content = """
            안드로이드 개발자라면, '클린 아키텍처'라는 말을 모두 들어보셨을겁니다.

            그래서 많은 개발자들이 '클린 아키텍처'로 앱을 개발하고 있다고 말합니다.

            하지만 진짜 클린 아키텍처 구조로 앱을 만들고 있는 사람은 많지 않습니다.

            '클린 아키텍처'라고 말하는 사람들의 실제 프로젝트는 '클린 아키텍처'가 아니기 때문입니다.

            어느정도 눈치 채셨겠지만
            Google의 '앱 아키텍처'와 '클린 아키텍처'는 완전히 다른 설계입니다.

            이 발표를 통해 각각의 아키텍처가 추구하는 방향과 차이점을 이해하고 본인에게 적합한 아키텍처를 선택할 수 있게 될겁니다.

            '클린 아키텍처'라는 것이 왜 중요하고 어떤 점을 가장 중요하게 생각해야 하는지에 대해서도 이야기합니다.
        """.trimIndent(),
        speakers = listOf(
            Speaker(
                name = "박상권",
                introduction = """
                    현) 헤이딜러 안드로이드팀 리더

                    저는 사람들이 비효율과 불합리를 당연히 여기지 않도록 돕고, 더 나은 선택과 환경을 만들어갈 수 있도록 노력합니다.

                    이를 통해 공정하게 대우받으며, 좋은 것을 공유하고 함께 누리는 세상을 꿈꿉니다.

                    개발, 강의, 블로그, 오픈소스, 조직문화 개선등 제가 하는 모든 일은 이러한 세상을 만드는 데 기여하기 위한 실천입니다.
                """.trimIndent(),
                imageUrl = "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/2025/app/storage/speaker/박상권.jpeg",
            ),
        ),
        tags = listOf(Tag("아키텍처")),
        room = Room.TRACK1,
        startTime = LocalDateTime.parse("2025-06-17T15:35:00"),
        endTime = LocalDateTime.parse("2025-06-17T16:20:00"),
        isBookmarked = true,
    )

    KnightsTheme {
        Surface {
            SessionDetailContent(session = sampleSession)
        }
    }
}
