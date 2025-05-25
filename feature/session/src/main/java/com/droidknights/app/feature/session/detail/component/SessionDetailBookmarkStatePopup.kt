package com.droidknights.app.feature.session.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
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
        shape = RoundedCornerShape(4.dp),
        color = KnightsColor.Graphite,
        contentColor = KnightsColor.Blue02,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 13.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flagbookmark),
                contentDescription = null,
                modifier = Modifier
                    .size(10.dp, 14.dp)
            )

            Text(
                text = stringResource(id = messageStringRes),
                style = KnightsTheme.typography.titleSmallM140,
            )
        }
    }
}

@Preview
@Composable
private fun BookmarkStatePopupPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SessionDetailBookmarkStatePopup(bookmarked = true)
        SessionDetailBookmarkStatePopup(bookmarked = false)
    }
}
