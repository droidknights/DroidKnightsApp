package com.droidknights.app2023.feature.session

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.component.TextChip
import com.droidknights.app2023.core.designsystem.theme.DarkGray
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LightGray
import com.droidknights.app2023.core.model.Room
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * TODO: 데이터 연결
 */
@Composable
internal fun SessionCard(
    modifier: Modifier = Modifier,
) {
    KnightsCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(CardContentPadding)
        ) {
            // 카테고리
            Row(verticalAlignment = Alignment.CenterVertically) {
                CategoryChip()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "효율적인 코드 베이스",
                    style = KnightsTheme.typography.labelLargeM,
                    color = DarkGray,
                )
            }

            // 제목
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Jetpack Compose에 있는 것, 없는 것",
                style = KnightsTheme.typography.titleLargeB,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(end = 50.dp)
            )

            // 태그
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                TrackChip(room = Room.TRACK1)
                Spacer(modifier = Modifier.width(8.dp))
                TimeChip(LocalTime.of(16, 45))
            }

            // 발표자
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Text(
                    text = "안성용",
                    style = KnightsTheme.typography.titleLargeB,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .weight(1f),
                )
                NetworkImage(
                    imageUrl = "https://picsum.photos/200",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    placeholder = painterResource(id = com.droidknights.app2023.core.designsystem.R.drawable.placeholder_speaker),
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
private fun TrackChip(room: Room) {
    val text = when (room) {
        Room.ETC -> stringResource(id = R.string.session_keynote)
        Room.TRACK1 -> stringResource(id = R.string.session_track_01)
        Room.TRACK2 -> stringResource(id = R.string.session_track_02)
        Room.TRACK3 -> stringResource(id = R.string.session_track_03)
    }

    TextChip(
        text = text,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
private fun TimeChip(time: LocalTime) {
    val pattern = stringResource(id = R.string.session_time_fmt)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
    TextChip(
        text = formatter.format(time),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )
}

private val CardContentPadding =
    PaddingValues(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)

@Preview
@Composable
private fun SessionCardPreview() {
    KnightsTheme {
        SessionCard()
    }
}
