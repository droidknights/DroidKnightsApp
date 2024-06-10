package com.droidknights.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.designsystem.theme.White
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.model.Session
import com.droidknights.app.feature.bookmark.R
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.datetime.LocalDateTime

@Composable
internal fun EditModeLeadingItem(
    itemState: BookmarkItemUiState,
    selectedSessionIds: ImmutableSet<String>,
    onSelectedItem: (Session) -> Unit,
) {
    val isSelectedItem = selectedSessionIds.contains(itemState.session.id)
    val baseModifier = Modifier
        .padding(horizontal = 18.dp)
        .size(24.dp)
        .clip(CircleShape)
        .clickable { onSelectedItem(itemState.session) }
    if (isSelectedItem) {
        Box(
            modifier = baseModifier.background(Purple01),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = White
            )
        }
    } else {
        Box(
            modifier = baseModifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = CircleShape
                )
        )
    }
}

private val SampleBookmarkItemUiState = BookmarkItemUiState(
    index = 0,
    session = Session(
        id = "1",
        title = "Session Title",
        content = "Compose 성능 최적화를 위한 Stability 마스터하기",
        speakers = emptyList(),
        tags = emptyList(),
        room = Room.TRACK1,
        startTime = LocalDateTime(2022, 1, 1, 10, 0, 0),
        endTime = LocalDateTime(2022, 1, 1, 11, 0, 0),
        isBookmarked = true,
    ),
)

private val SampleSelectedSessionIds = persistentSetOf("1", "2", "3")
private val SampleUnselectedSessionIds = persistentSetOf("-1")

@Preview
@Composable
private fun CheckedEditModeLeadingItemPreview() {
    KnightsTheme {
        EditModeLeadingItem(
            itemState = SampleBookmarkItemUiState,
            selectedSessionIds = SampleSelectedSessionIds,
            onSelectedItem = {}
        )
    }
}

@Preview
@Composable
private fun UncheckedEditModeLeadingItemPreview() {
    KnightsTheme {
        EditModeLeadingItem(
            itemState = SampleBookmarkItemUiState,
            selectedSessionIds = SampleUnselectedSessionIds,
            onSelectedItem = {}
        )
    }
}
