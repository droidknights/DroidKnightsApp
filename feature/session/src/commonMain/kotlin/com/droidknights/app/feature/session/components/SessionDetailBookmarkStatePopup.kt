package com.droidknights.app.feature.session.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.ic_flagbookmark
import droidknights.feature.session.generated.resources.session_detail_bookmark_popup_message
import droidknights.feature.session.generated.resources.session_detail_unbookmark_popup_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SessionDetailBookmarkStatePopup(bookmarked: Boolean) {
    val messageStringRes by rememberUpdatedState(
        newValue = if (bookmarked) {
            Res.string.session_detail_bookmark_popup_message
        } else {
            Res.string.session_detail_unbookmark_popup_message
        },
    )

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = KnightsTheme.colorScheme.darkSurface,
        contentColor = KnightsTheme.colorScheme.onSecondarySurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 13.dp),
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_flagbookmark),
                contentDescription = null,
                modifier = Modifier
                    .size(10.dp, 14.dp),
            )

            Text(
                text = stringResource(resource = messageStringRes),
                style = KnightsTheme.typography.titleSmallM140,
            )
        }
    }
}
