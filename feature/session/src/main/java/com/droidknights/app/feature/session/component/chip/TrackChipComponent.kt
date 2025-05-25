package com.droidknights.app.feature.session.component.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.ui.textRes

@Composable
internal fun TrackChip(text: String) {
    TextChip(
        text = text,
        containerColor = KnightsColor.Blue01,
        labelColor = KnightsColor.White,
    )
}

@Composable
internal fun TrackChip(stringRes: Int) {
    TrackChip(
        text = stringResource(id = stringRes),
    )
}

@Preview
@Composable
private fun PreviewTrackChip(@PreviewParameter(TrackChipPreviewParameterProvider::class) room: Room) {
    TrackChip(room.textRes)
}

private class TrackChipPreviewParameterProvider : PreviewParameterProvider<Room> {
    override val values = sequenceOf(
        Room.TRACK1,
        Room.TRACK2,
        Room.ETC,
    )
}
