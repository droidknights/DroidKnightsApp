package com.droidknights.app.feature.session.components

import androidx.compose.runtime.Composable
import com.droidknights.app.core.designsystem.components.Chip
import com.droidknights.app.core.designsystem.components.ChipStyle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Tag
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.session_room_etc
import droidknights.feature.session.generated.resources.session_room_track_01
import droidknights.feature.session.generated.resources.session_room_track_02
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TagChip(tag: Tag) {
    Chip(text = tag.name)
}

@Composable
internal fun TrackChip(room: Room) {
    Chip(
        text = when (room) {
            Room.TRACK1 -> stringResource(resource = Res.string.session_room_track_01)
            Room.TRACK2 -> stringResource(resource = Res.string.session_room_track_02)
            Room.TRACK3 -> stringResource(resource = Res.string.session_room_track_02) // TODO: 삭제하기
            Room.ETC -> stringResource(resource = Res.string.session_room_etc)
        },
        style = ChipStyle.Primary,
    )
}

@Composable
internal fun TimeChip(dateTime: LocalDateTime) {
    // TODO 패턴 기반 formatting
//    val pattern = stringResource(resource = Res.string.session_time_fmt)
//    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
//    val time = remember { dateTime.toJavaLocalDateTime().toLocalTime() }

    Chip(
//        text = formatter.format(time),
        text = "${dateTime.hour}:${dateTime.minute} 발표",
        style = ChipStyle.Secondary,
    )
}

@Composable
internal fun CategoryChip(text: String) {
    Chip(
        text = text,
        style = ChipStyle.Border,
    )
}

@Preview
@Composable
private fun TagChipPreview() {
    KnightsTheme {
        TagChip(Tag("Android"))
    }
}

@Preview
@Composable
private fun TrackChipPreview() {
    KnightsTheme {
        TrackChip(Room.TRACK1)
    }
}

@Preview
@Composable
private fun TimeChipPreview() {
    KnightsTheme {
        TimeChip(LocalDateTime(2022, 1, 1, 10, 22))
    }
}
