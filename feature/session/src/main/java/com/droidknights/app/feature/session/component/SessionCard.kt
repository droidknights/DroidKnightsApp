package com.droidknights.app.feature.session.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.IconTextChip
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.theme.DarkGray
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.designsystem.theme.Purple01A30
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.model.Session
import com.droidknights.app.core.model.Speaker
import com.droidknights.app.core.model.Tag
import com.droidknights.app.feature.session.R
import kotlinx.datetime.LocalDateTime

@Composable
internal fun SessionCard(
    session: Session,
    modifier: Modifier = Modifier,
    onSessionClick: (Session) -> Unit = { },
) {
    if (session.content.isBlank()) {
        KnightsCard(
            modifier = modifier
        ) {
            SessionCardContent(session = session)
        }
    } else {
        KnightsCard(
            modifier = modifier,
            onClick = { onSessionClick(session) }
        ) {
            SessionCardContent(session = session)
        }
    }
}

@Composable
private fun SessionCardContent(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        if (session.isBookmarked) {
            BookmarkImage(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 30.dp)
            )
        }
        Column(
            modifier = Modifier.padding(CardContentPadding)
        ) {
            SessionHeader(session)
            Spacer(modifier = Modifier.height(8.dp))
            SessionTitle(session.title)
            Spacer(modifier = Modifier.height(12.dp))
            SessionTrackInfo(session)
            Spacer(modifier = Modifier.height(12.dp))
            SessionSpeakers(session.speakers)
        }
    }
}

@Composable
private fun SessionHeader(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryChip()
        session.tags.forEach { tag ->
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = tag.name,
                style = KnightsTheme.typography.labelLargeM,
                color = DarkGray,
            )
        }
    }
}

@Composable
private fun SessionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = KnightsTheme.typography.titleLargeB,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = modifier.padding(end = 50.dp)
    )
}

@Composable
private fun SessionTrackInfo(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        TrackChip(room = session.room)
        Spacer(modifier = Modifier.width(8.dp))
        TimeChip(dateTime = session.startTime)
        if (session.isBookmarked) {
            Spacer(modifier = Modifier.width(8.dp))
            IconTextChip(
                text = stringResource(id = R.string.bookmark),
                containerColor = Purple01A30,
                labelColor = Purple01,
                iconPainter = painterResource(id = R.drawable.ic_session_bookmark_filled),
                iconTint = Purple01
            )
        }
    }
}

@Composable
private fun SessionSpeakers(
    speakers: List<Speaker>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            speakers.forEach { speaker ->
                Text(
                    text = speaker.name,
                    style = KnightsTheme.typography.titleLargeB,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            speakers.forEach { speaker ->
                NetworkImage(
                    imageUrl = speaker.imageUrl,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    placeholder = painterResource(id = com.droidknights.app.core.ui.R.drawable.placeholder_speaker),
                )
            }
        }
    }
}

@Composable
private fun CategoryChip() {
    TextChip(
        text = stringResource(id = R.string.session_category),
        containerColor = DarkGray,
        labelColor = LightGray,
    )
}

@Composable
private fun BookmarkImage(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_flagbookmark),
        contentDescription = null,
        modifier = modifier
            .size(
                width = 24.dp,
                height = 36.dp
            )
    )
}

private val CardContentPadding =
    PaddingValues(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)

internal class SessionPreviewParameterProvider : PreviewParameterProvider<Session> {
    override val values = sequenceOf(
        Session( // single speaker with out bookmark
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = false,
        ),
        Session( // single speaker with bookmark
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = true,
        ),
        Session( // multi speakers
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스"),
                Tag("효율적인 코드베이스"),
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = false,
        ),
    )
}

@Preview
@Composable
private fun SessionCardPreview(
    @PreviewParameter(SessionPreviewParameterProvider::class) session: Session,
) {
    KnightsTheme {
        SessionCard(session)
    }
}
