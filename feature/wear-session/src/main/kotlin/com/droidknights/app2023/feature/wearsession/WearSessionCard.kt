package com.droidknights.app2023.feature.wearsession

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.model.Speaker
import com.droidknights.app2023.core.model.Tag
import com.droidknights.app2023.core.model.Video
import kotlinx.datetime.LocalDateTime

@Composable
internal fun SessionCard(
    session: Session,
    modifier: Modifier = Modifier,
    onSessionClick: (Session) -> Unit = { },
) {
    if (session.video.isReady) {
        KnightsCard(
            modifier = modifier,
            onClick = { onSessionClick(session) }
        ) {
            SessionCardContent(session = session)
        }
    } else {
        KnightsCard(
            modifier = modifier
        ) {
            SessionCardContent(session = session)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SessionCardContent(
    session: Session,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NetworkImage(
            imageUrl = session.speakers.firstOrNull()?.imageUrl ?: DefaultImageUrl,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = com.droidknights.app2023.core.ui.R.drawable.placeholder_speaker),
        )
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // 제목
            Text(
                modifier = Modifier.basicMarquee(),
                text = session.title,
                style = KnightsTheme.typography.titleMediumB,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // 발표자
            session.speakers
                .joinToString(",") { it.name }
                .takeIf { it.isNotBlank() }
                ?.let { speakerName ->
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = speakerName,
                        style = KnightsTheme.typography.bodyMediumR,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
        }
    }

}

@Preview
@Composable
private fun SessionCardPreview() {
    val fakeSession = Session(
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
        level = Level.BASIC,
        tags = listOf(
            Tag("효율적인 코드베이스")
        ),
        startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
        endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
        room = Room.TRACK1,
        video = Video.None
    )

    KnightsTheme {
        SessionCard(fakeSession)
    }
}

private const val DefaultImageUrl = "https://raw.githubusercontent.com/workspace/media-samples/main/img/logo.jpg"
