package com.droidknights.app2023.feature.bookmark.text

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.feature.bookmark.R


internal val Room.textRes: Int
    get() = when (this) {
        Room.ETC -> R.string.session_room_keynote
        Room.TRACK1 -> R.string.session_room_track_01
        Room.TRACK2 -> R.string.session_room_track_02
        Room.TRACK3 -> R.string.session_room_track_03
    }

@Composable
internal fun RoomText(
    room: Room,
    style: TextStyle,
    color: Color = LocalContentColor.current
) {
    Text(
        text = stringResource(id = room.textRes),
        style = style,
        color = color
    )
}