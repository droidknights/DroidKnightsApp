package com.droidknights.app2023.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.Purple01
import com.droidknights.app2023.core.designsystem.theme.White
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
internal fun BookmarkTimelineItem(
    modifier: Modifier = Modifier,
    sequence: Int,
    time: LocalTime
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SequenceBadge(sequence = sequence)
        Spacer(modifier = Modifier.height(8.dp))
        SessionTimeBadge(time = time)
    }
}

@Composable
private fun SequenceBadge(
    modifier: Modifier = Modifier,
    sequence: Int
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(color = Purple01.copy(alpha = 0.3F), shape = CircleShape)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = sequence.toString(),
            style = KnightsTheme.typography.labelLargeM,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SessionTimeBadge(
    modifier: Modifier = Modifier,
    time: LocalTime
) {
    val pattern = stringResource(id = R.string.session_time_format)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }

    Box(
        modifier = modifier
            .background(color = Purple01, shape = RoundedCornerShape(percent = 50))
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = formatter.format(time),
            style = KnightsTheme.typography.labelSmallM,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F9F9)
@Composable
private fun BookmarkTimelineItemPreview() {
    KnightsTheme {
        Box {
            BookmarkTimelineItem(
                sequence = 1,
                time = LocalTime.of(23, 40)
            )
        }
    }
}
