package com.droidknights.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.designsystem.theme.White
import droidknights.feature.bookmark.generated.resources.Res
import droidknights.feature.bookmark.generated.resources.session_time_format
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BookmarkTimelineItem(
    sequence: Int,
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SequenceBadge(sequence = sequence)
        Spacer(modifier = Modifier.height(8.dp))
        SessionTimeBadge(time = time)
    }
}

@Composable
private fun SequenceBadge(
    sequence: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(color = Purple01.copy(alpha = 0.3F), shape = CircleShape),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = sequence.toString(),
            style = KnightsTheme.typography.labelLargeM,
            color = White,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
private fun SessionTimeBadge(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    val pattern = stringResource(Res.string.session_time_format)

    Box(
        modifier = modifier
            .background(color = Purple01, shape = RoundedCornerShape(percent = 50))
            .padding(vertical = 2.dp, horizontal = 8.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = time.format(LocalTime.Format { byUnicodePattern(pattern) }),
            style = KnightsTheme.typography.labelSmallM,
            color = White,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun BookmarkTimelineItemLightPreview() {
    KnightsTheme(darkTheme = false) {
        Box(modifier = Modifier.background(KnightsTheme.colorScheme.background)) {
            BookmarkTimelineItem(
                sequence = 1,
                time = LocalTime(23, 40),
            )
        }
    }
}

@Preview
@Composable
private fun BookmarkTimelineItemDarkPreview() {
    KnightsTheme(darkTheme = true) {
        Box(modifier = Modifier.background(KnightsTheme.colorScheme.background)) {
            BookmarkTimelineItem(
                sequence = 1,
                time = LocalTime(23, 40),
            )
        }
    }
}
