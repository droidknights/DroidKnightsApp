package com.droidknights.app2023.core.ui

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import com.droidknights.app2023.core.model.Room

val Room.textRes: Int
    get() = when (this) {
        Room.ETC -> R.string.session_room_keynote
        Room.TRACK1 -> R.string.session_room_track_01
        Room.TRACK2 -> R.string.session_room_track_02
        Room.TRACK3 -> R.string.session_room_track_03
    }

@Composable
fun RoomText(
    room: Room,
    style: TextStyle,
    color: Color = LocalContentColor.current,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = stringResource(id = room.textRes),
        style = style,
        color = color,
        onTextLayout = onTextLayout,
    )
}
