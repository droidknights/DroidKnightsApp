package com.droidknights.app.feature.session.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Chip
import com.droidknights.app.core.designsystem.components.ChipStyle
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_flagbookmark
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.bookmark
import droidknights.feature.session.generated.resources.session_category
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun SessionCard(
    session: Session,
    modifier: Modifier = Modifier,
    onSessionClick: ((Session) -> Unit)? = null,
) {
    if (onSessionClick != null) {
        Surface(
            onClick = {
                onSessionClick(session)
            },
            modifier = modifier,
            color = KnightsTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp),
        ) {
            SessionCardContent(session = session)
        }
    } else {
        Surface(
            modifier = modifier,
            color = KnightsTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp),
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
        modifier = modifier,
    ) {
        if (session.isBookmarked) {
            BookmarkImage(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 30.dp),
            )
        }
        Column(
            modifier = Modifier.padding(CardContentPadding),
        ) {
            SessionHeader(session)
            Spacer(modifier = Modifier.height(8.dp))
            SessionTitle(session.title)
            Spacer(modifier = Modifier.height(12.dp))
            SessionTrackInfo(session)
            Spacer(modifier = Modifier.height(12.dp))
            SessionSpeakers(session)
        }
    }
}

@Composable
private fun SessionHeader(
    session: Session,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CategoryChip()
        session.tags.forEach { tag ->
            Text(
                text = tag.name,
                style = KnightsTheme.typography.labelLargeM,
                color = LocalContentColor.current.copy(alpha = 0.5F),
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
        modifier = modifier.padding(end = 50.dp),
    )
}

@Composable
private fun SessionTrackInfo(
    session: Session,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TrackChip(room = session.room)
        TimeChip(dateTime = session.startTime)
        if (session.isBookmarked) {
            Chip(
                text = stringResource(resource = Res.string.bookmark),
                icon = painterResource(resource = DesignRes.drawable.ic_flagbookmark),
                style = ChipStyle.Accent,
            )
        }
    }
}

@Composable
private fun SessionSpeakers(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            session.speakers.forEach { speaker ->
                Text(
                    text = speaker.name,
                    style = KnightsTheme.typography.titleLargeB,
                )
            }
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            session.speakers.forEach { speaker ->
                NetworkImage(
                    imageUrl = speaker.imageUrl,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                )
            }
        }
    }
}

@Composable
private fun CategoryChip() {
    Chip(
        text = stringResource(resource = Res.string.session_category),
        style = ChipStyle.Border,
    )
}

@Composable
private fun BookmarkImage(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(resource = DesignRes.drawable.ic_flagbookmark),
        contentDescription = null,
        modifier = modifier
            .size(
                width = 24.dp,
                height = 36.dp,
            ),
    )
}

private val CardContentPadding =
    PaddingValues(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)

@Preview
@Composable
private fun SessionCardPreview() {
    KnightsTheme {
        SessionCard(
            Session(
                id = "1",
                title = "세션 타이틀 1",
                content = "세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 ",
                speakers = listOf(
                    Speaker(
                        name = "세션 연사자 1",
                        introduction = "세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1",
                        imageUrl = "",
                    ),
                ),
                tags = listOf(Tag("Android")),
                room = Room.TRACK1,
                startTime = LocalDateTime(2025, 6, 17, 11, 0),
                endTime = LocalDateTime(2025, 6, 17, 12, 0),
                isBookmarked = true,
            ),
        )
    }
}
