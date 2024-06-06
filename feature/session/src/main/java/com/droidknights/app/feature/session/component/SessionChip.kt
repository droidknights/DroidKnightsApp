package com.droidknights.app.feature.session.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.ui.textRes
import com.droidknights.app.feature.session.R
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun TrackChip(room: Room) {
    TextChip(
        text = stringResource(id = room.textRes),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
internal fun TimeChip(dateTime: LocalDateTime) {
    val pattern = stringResource(id = R.string.session_time_fmt)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
    val time = remember { dateTime.toJavaLocalDateTime().toLocalTime() }

    TextChip(
        text = formatter.format(time),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )
}

internal class RoomPreviewParameterProvider : PreviewParameterProvider<Room> {
    override val values = sequenceOf(
        Room.TRACK1,
        Room.TRACK2,
        Room.TRACK3,
        Room.ETC
    )
}

@Preview
@Composable
fun TrackChipPreview(
    @PreviewParameter(RoomPreviewParameterProvider::class) room: Room,
) {
    TrackChip(room)
}

@Preview
@Composable
fun TimeChipPreview() {
    TimeChip(LocalDateTime(2022, 1, 1, 10, 22))
}
