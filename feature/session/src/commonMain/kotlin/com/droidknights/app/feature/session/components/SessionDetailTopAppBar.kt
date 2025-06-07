package com.droidknights.app.feature.session.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.IconButton
import com.droidknights.app.core.designsystem.components.TopAppBar
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_arrow_back
import droidknights.core.designsystem.generated.resources.ic_session_bookmark
import droidknights.core.designsystem.generated.resources.ic_session_bookmark_filled
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.session_detail_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SessionDetailTopAppBar(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = stringResource(Res.string.session_detail_title),
        navigation = {
            IconButton(
                onClick = onBackClick,
                modifier = it,
            ) {
                Icon(
                    painter = painterResource(DesignRes.drawable.ic_arrow_back),
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
                        painterResource(DesignRes.drawable.ic_session_bookmark_filled)
                    } else {
                        painterResource(DesignRes.drawable.ic_session_bookmark)
                    },
                    contentDescription = null,
                    tint = if (bookmarked) KnightsTheme.colorScheme.primary else Color.Unspecified,
                )
            }
        },
    )
}
