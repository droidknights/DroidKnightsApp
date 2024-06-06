package com.droidknights.app.feature.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app.core.designsystem.theme.DarkGray
import com.droidknights.app.core.designsystem.theme.Gray
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.model.Session
import com.droidknights.app.core.model.Speaker
import com.droidknights.app.core.model.Tag
import com.droidknights.app.feature.session.component.SessionDetailBookmarkStatePopup
import com.droidknights.app.feature.session.component.TimeChip
import com.droidknights.app.feature.session.component.TrackChip
import com.droidknights.app.feature.session.model.SessionDetailEffect
import com.droidknights.app.feature.session.model.SessionDetailUiState
import com.droidknights.app.widget.sendWidgetUpdateCommand
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    viewModel: SessionDetailViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val sessionUiState by viewModel.sessionUiState.collectAsStateWithLifecycle()
    val effect by viewModel.sessionUiEffect.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(effect) {
        if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
            sendWidgetUpdateCommand(context)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
            .verticalScroll(scrollState),
    ) {
        SessionDetailTopAppBar(
            bookmarked = (sessionUiState as? SessionDetailUiState.Success)?.bookmarked ?: false,
            onClickBookmark = { viewModel.toggleBookmark() },
            onBackClick = onBackClick
        )
        Box {
            SessionDetailContent(uiState = sessionUiState)
            if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
                SessionDetailBookmarkStatePopup(
                    bookmarked = (effect as SessionDetailEffect.ShowToastForBookmarkState).bookmarked
                )
            }
        }
    }

    LaunchedEffect(sessionId) {
        viewModel.fetchSession(sessionId)
    }

    LaunchedEffect(effect) {
        if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
            delay(1000L)
            viewModel.hidePopup()
        }
    }
}

@Composable
private fun SessionDetailTopAppBar(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {
    KnightsTopAppBar(
        titleRes = R.string.session_detail_title,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.Back,
        actionButtons = {
            BookmarkToggleButton(
                bookmarked = bookmarked,
                onClickBookmark = onClickBookmark
            )
        },
        onNavigationClick = onBackClick,
    )
}

@Composable
private fun SessionDetailContent(uiState: SessionDetailUiState) {
    when (uiState) {
        is SessionDetailUiState.Loading -> SessionDetailLoading()
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
        Spacer(modifier = Modifier.height(12.dp))
        SessionChips(session = session)

        if (session.content.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SessionOverview(content = session.content)
        }
        Spacer(modifier = Modifier.height(40.dp))
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(40.dp))

        session.speakers.forEach { speaker ->
            SessionDetailSpeaker(speaker)
            if (speaker != session.speakers.last()) {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun SessionChips(session: Session) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TrackChip(room = session.room)
        TimeChip(dateTime = session.startTime)
        TagChips(tags = session.tags.toPersistentList())
    }
}

@Composable
private fun TagChips(tags: PersistentList<Tag>) {
    tags.forEach { tag ->
        TagChip(tag = tag)
    }
}

@Composable
private fun TagChip(tag: Tag) {
    TextChip(
        text = tag.name,
        containerColor = DarkGray,
        labelColor = LightGray,
    )
}

@Composable
private fun SessionDetailTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(end = 58.dp),
        text = title,
        style = KnightsTheme.typography.headlineMediumB,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
private fun SessionDetailSpeaker(
    speaker: Speaker,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        NetworkImage(
            imageUrl = speaker.imageUrl,
            modifier = Modifier
                .size(108.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = com.droidknights.app.core.ui.R.drawable.placeholder_speaker)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.session_detail_speaker),
            style = KnightsTheme.typography.labelSmallM,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = speaker.name,
            style = KnightsTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = speaker.introduction,
            style = KnightsTheme.typography.titleSmallR140,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@Composable
private fun SessionOverview(content: String) {
    Column {
        Text(
            text = stringResource(id = R.string.session_overview_title),
            style = KnightsTheme.typography.titleSmallB,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = content,
            style = KnightsTheme.typography.titleSmallR140,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun BookmarkToggleButton(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = bookmarked,
        onCheckedChange = onClickBookmark
    ) {
        Icon(
            painter =
            if (bookmarked) {
                painterResource(id = R.drawable.ic_session_bookmark_filled)
            } else {
                painterResource(id = R.drawable.ic_session_bookmark)
            },
            contentDescription = null,
            tint = if (bookmarked) Purple01 else Gray
        )
    }
}

private val SampleSessionHasContent = Session(
    id = "2",
    title = "세션 제목은 세션 제목 - 개요 있음",
    content = "세션에 대한 소개와 세션에서의 장단점과 세션을 실제로 사용한 사례와 세션 내용에 대한 QnA 진행",
    speakers = listOf(
        Speaker(
            name = "스피커1",
            introduction = "스피커1 에 대한 소개",
            imageUrl = "",
        ),
    ),
    tags = listOf(Tag("Dev Environment")),
    room = Room.TRACK1,
    startTime = LocalDateTime.parse("2023-09-12T11:00:00.000"),
    endTime = LocalDateTime.parse("2023-09-12T11:30:00.000"),
    isBookmarked = false
)

private val SampleSessionNoContent = Session(
    id = "2",
    title = "세션 제목은 세션 제목 - 개요 없음",
    content = "",
    speakers = listOf(
        Speaker(
            name = "스피커1",
            introduction = "스피커1 에 대한 소개",
            imageUrl = "",
        ),
    ),
    tags = listOf(Tag("Dev Environment")),
    room = Room.TRACK1,
    startTime = LocalDateTime.parse("2023-09-12T11:00:00.000"),
    endTime = LocalDateTime.parse("2023-09-12T11:30:00.000"),
    isBookmarked = true
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
        var state by remember { mutableStateOf(false) }
        SessionDetailTopAppBar(
            bookmarked = state,
            onClickBookmark = {
                state = it
            }
        ) { }
    }
}

@Preview
@Composable
private fun SessionDetailContentPreview(
    @PreviewParameter(SessionDetailContentProvider::class) session: Session,
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
        SessionDetailSpeaker(SampleSessionHasContent.speakers.first())
    }
}

@Preview
@Composable
private fun SessionOverviewPreview() {
    KnightsTheme {
        SessionOverview(SampleSessionHasContent.content)
    }
}
