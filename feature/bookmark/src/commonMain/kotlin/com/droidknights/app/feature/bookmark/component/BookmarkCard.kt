package com.droidknights.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.model.session.Room
import droidknights.feature.bookmark.generated.resources.Res
import droidknights.feature.bookmark.generated.resources.session_room_keynote
import droidknights.feature.bookmark.generated.resources.session_room_track_01
import droidknights.feature.bookmark.generated.resources.session_room_track_02
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

val Room.textRes: StringResource
    get() = when (this) {
        Room.ETC -> Res.string.session_room_keynote
        Room.TRACK1 -> Res.string.session_room_track_01
        Room.TRACK2 -> Res.string.session_room_track_02
    }

@Composable
internal fun BookmarkCard(
    tagLabel: String,
    room: Room,
    title: String,
    speaker: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = KnightsTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(color = Purple01, shape = CircleShape)
                    .size(12.dp),
            )
            Text(
                modifier = Modifier.weight(1F),
                text = tagLabel,
                style = KnightsTheme.typography.labelSmallM,
                color = KnightsTheme.colorScheme.neutralSurface,
            )
            Text(
                stringResource(room.textRes),
                style = KnightsTheme.typography.labelSmallM,
                color = KnightsTheme.colorScheme.neutralSurface,
            )
        }

        Text(
            text = title,
            style = KnightsTheme.typography.titleSmallB,
            color = KnightsTheme.colorScheme.onSurface,
        )

        Text(
            text = speaker,
            style = KnightsTheme.typography.labelSmallM,
            color = KnightsTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun BookmarkCardLightPreview() {
    KnightsTheme(darkTheme = false) {
        Box {
            BookmarkCard(
                tagLabel = "효율적인 코드 베이스",
                room = Room.TRACK2,
                title = "Jetpack Compose에 있는 것, 없는것",
                speaker = "홍길동",
            )
        }
    }
}

@Preview
@Composable
private fun BookmarkCardDarkPreview() {
    KnightsTheme(darkTheme = true) {
        Box {
            BookmarkCard(
                tagLabel = "효율적인 코드 베이스",
                room = Room.TRACK2,
                title = "Jetpack Compose에 있는 것, 없는것",
                speaker = "홍길동",
            )
        }
    }
}
