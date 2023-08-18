package com.droidknights.app2023.feature.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.surfaceDim
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.model.Speaker
import com.droidknights.app2023.core.model.Tag
import kotlinx.datetime.LocalDateTime

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    viewModel: SessionDetailViewModel = hiltViewModel(),
) {
    val sessionUiState by viewModel.sessionUiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding(),
    ) {
        SessionDetailTopAppBar(onBackClick = onBackClick)
        SessionDetailContent(uiState = sessionUiState)
    }

    LaunchedEffect(sessionId) {
        viewModel.fetchSession(sessionId)
    }
}

@Composable
private fun SessionDetailTopAppBar(
    onBackClick: () -> Unit,
) {
    KnightsTopAppBar(
        titleRes = R.string.session_detail_title,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.Back,
        onNavigationClick = onBackClick,
    )

    // TODO: 북마크 확인 및 변경 기능 추가
}

@Composable
private fun SessionDetailContent(uiState: SessionDetailUiState) {
    when (uiState) {
        SessionDetailUiState.Loading -> SessionDetailLoading()
        is SessionDetailUiState.Success -> SessionDetailContent(uiState.session)
    }
}

@Composable
private fun SessionDetailLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SessionDetailContent(session: Session) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SessionDetailTitle(title = session.title, modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        SessionChips(session = session)
        if (session.content.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SessionOverview(content = session.content)
            Spacer(modifier = Modifier.height(56.dp))
        } else {
            Spacer(modifier = Modifier.height(40.dp))
        }
        SessionDetailSpeaker(session.speakers)
    }
}

@Composable
private fun SessionDetailTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(end = 64.dp),
        text = title,
        style = KnightsTheme.typography.headlineMediumB,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
private fun SessionDetailSpeaker(
    speakers: List<Speaker>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.session_detail_speaker),
            style = KnightsTheme.typography.labelSmallM,
            color = MaterialTheme.colorScheme.onSurface,
        )
        speakers.forEach { speaker ->
            Text(
                text = speaker.name,
                style = KnightsTheme.typography.titleMediumB,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(Modifier.height(8.dp))
        NetworkImage(
            imageUrl = speakers.first().imageUrl,
            modifier = Modifier
                .size(108.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = com.droidknights.app2023.core.designsystem.R.drawable.placeholder_speaker)
        )
    }
}

@Composable
private fun SessionOverview(content: String) {
    Text(
        text = content,
        style = KnightsTheme.typography.titleSmallR140,
        color = MaterialTheme.colorScheme.onSurface
    )
}

private val SampleSessionHasContent = Session(
    id = "2",
    title = "세션 제목은 세션 제목 - 개요 있음",
    content = "세션에 대한 소개와 세션에서의 장단점과 세션을 실제로 사용한 사례와 세션 내용에 대한 QnA 진행",
    speakers = listOf(
        Speaker(name = "스피커1", "https://raw.githubusercontent.com/droidknights/DroidKnights2023_App/main/storage/speaker/차영호.png"),
        Speaker(name = "스피커2", "https://raw.githubusercontent.com/droidknights/DroidKnights2023_App/main/storage/speaker/차영호.png")
    ),
    level = Level.ADVANCED,
    tags = listOf(Tag("Dev Environment")),
    room = Room.TRACK1,
    startTime = LocalDateTime.parse("2023-09-12T11:00:00.000"),
    endTime = LocalDateTime.parse("2023-09-12T11:30:00.000")
)

private val SampleSessionNoContent = Session(
    id = "2",
    title = "세션 제목은 세션 제목 - 개요 없음",
    content = "",
    speakers = listOf(
        Speaker(name = "스피커1", "https://raw.githubusercontent.com/droidknights/DroidKnights2023_App/main/storage/speaker/차영호.png"),
        Speaker(name = "스피커2", "https://raw.githubusercontent.com/droidknights/DroidKnights2023_App/main/storage/speaker/차영호.png")
    ),
    level = Level.ADVANCED,
    tags = listOf(Tag("Dev Environment")),
    room = Room.TRACK1,
    startTime = LocalDateTime.parse("2023-09-12T11:00:00.000"),
    endTime = LocalDateTime.parse("2023-09-12T11:30:00.000")
)

class SessionDetailContentProvider : PreviewParameterProvider<Session> {
    override val values: Sequence<Session> = sequenceOf(
        SampleSessionNoContent,
        SampleSessionHasContent
    )
}

@Preview
@Composable
private fun SessionDetailTopAppBarPreview() {
    KnightsTheme {
        SessionDetailTopAppBar { }
    }
}

@Preview
@Composable
private fun SessionDetailContentPreview(
    @PreviewParameter(SessionDetailContentProvider::class) session: Session
) {
    KnightsTheme {
        SessionDetailContent(session = session)
    }
}

@Preview
@Composable
private fun SessionDetailTitlePreview() {
    KnightsTheme {
        SessionDetailTitle(title = SampleSessionHasContent.title)
    }
}

@Preview
@Composable
private fun SessionDetailSpeakerPreview() {
    KnightsTheme {
        SessionDetailSpeaker(SampleSessionHasContent.speakers)
    }
}

@Preview
@Composable
private fun SessionOverviewPreview() {
    KnightsTheme {
        SessionOverview(SampleSessionHasContent.content)
    }
}
