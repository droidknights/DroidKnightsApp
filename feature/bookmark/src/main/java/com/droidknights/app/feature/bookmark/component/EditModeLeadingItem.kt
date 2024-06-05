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
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.designsystem.theme.White
import com.droidknights.app.core.model.Session
import com.droidknights.app.feature.bookmark.R
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import kotlinx.collections.immutable.ImmutableSet

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
