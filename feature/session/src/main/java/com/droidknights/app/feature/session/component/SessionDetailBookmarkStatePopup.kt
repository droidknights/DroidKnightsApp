package com.droidknights.app.feature.session.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.Graphite
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.feature.session.R

@Composable
internal fun SessionDetailBookmarkStatePopup(bookmarked: Boolean) {
    val messageStringRes by rememberUpdatedState(
        newValue = if (bookmarked) {
            R.string.session_detail_bookmark_popup_message
        } else {
            R.string.session_detail_unbookmark_popup_message
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(4.dp),
        color = Graphite
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            text = stringResource(id = messageStringRes),
            style = KnightsTheme.typography.bodyMediumR,
            color = Purple01
        )
    }
}

@Preview
@Composable
private fun BookmarkStatePopupPreview() {
    Column {
        SessionDetailBookmarkStatePopup(bookmarked = true)
        SessionDetailBookmarkStatePopup(bookmarked = false)
    }
}
