package com.droidknights.app.feature.session.list.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.IconTextChip
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.component.OutlineChip
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.feature.session.R
import com.droidknights.app.feature.session.component.SessionPreviewParameterProvider
import com.droidknights.app.feature.session.component.chip.TimeChip
import com.droidknights.app.feature.session.component.chip.TrackChip
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun SessionCard(
    session: Session,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false,
    onSessionClick: (Session) -> Unit = {},
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isHighlighted) {
            KnightsColor.Blue03.copy(alpha = 180f)
        } else {
            MaterialTheme.colorScheme.surface
        },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing,
        ),
        label = "itemBackgroundColor",
    )
    KnightsCard(
        modifier = modifier,
        color = backgroundColor,
        onClick = { onSessionClick(session) }
    ) {
        SessionCardContent(session = session)
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
            SessionSpeakers(session.speakers.toPersistentList())
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
        OutlineChip(
            text = stringResource(id = R.string.session_category),
        )

        session.tags.forEach { tag ->
            Text(
                text = tag.name,
                style = KnightsTheme.typography.labelLargeM,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier
                    .padding(start = 8.dp)
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
        modifier = modifier
            .padding(end = 50.dp)
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
        TrackChip(
            text = session.room.name,
        )

        Spacer(
            modifier = Modifier
                .width(8.dp)
        )

        TimeChip(
            dateTime = session.startTime,
        )

        if (session.isBookmarked) {
            Spacer(modifier = Modifier.width(8.dp))

            IconTextChip(
                text = stringResource(id = R.string.bookmark),
                containerColor = KnightsColor.Purple01A30,
                labelColor = KnightsColor.Purple01,
                iconPainter = painterResource(id = R.drawable.ic_session_bookmark_filled),
                iconTint = KnightsColor.Purple01,
            )
        }
    }
}

@Composable
private fun SessionSpeakers(
    speakers: ImmutableList<Speaker>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            speakers.forEach { speaker ->
                Text(
                    text = speaker.name,
                    style = KnightsTheme.typography.titleLargeB,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            speakers.forEach { speaker ->
                NetworkImage(
                    imageUrl = speaker.imageUrl,
                    placeholder = painterResource(id = com.droidknights.app.core.ui.R.drawable.placeholder_speaker),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
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

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SessionCardPreview(@PreviewParameter(SessionPreviewParameterProvider::class) session: Session) {
    KnightsTheme {
        SessionCard(session)
    }
}
