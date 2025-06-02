package com.droidknights.app.feature.session.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.IconButton
import com.droidknights.app.core.designsystem.components.TopAppBar
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.core.designsystem.generated.resources.Res
import droidknights.core.designsystem.generated.resources.ic_arrow_back
import droidknights.core.designsystem.generated.resources.ic_session_bookmark
import droidknights.core.designsystem.generated.resources.ic_session_bookmark_filled
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SessionDetailTopAppBar(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = "세션 상세 정보",
        navigation = {
            IconButton(
                onClick = onBackClick,
                modifier = it,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onClickBookmark(!bookmarked) },
                modifier = it,
            ) {
                Icon(
                    painter = if (bookmarked) {
                        painterResource(Res.drawable.ic_session_bookmark_filled)
                    } else {
                        painterResource(Res.drawable.ic_session_bookmark)
                    },
                    contentDescription = null,
                    tint = if (bookmarked) KnightsTheme.colorScheme.primary else Color.Unspecified,
                )
            }
        },
    )
}
