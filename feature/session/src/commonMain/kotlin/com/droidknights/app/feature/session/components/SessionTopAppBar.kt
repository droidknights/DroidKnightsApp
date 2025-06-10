package com.droidknights.app.feature.session.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.IconButton
import com.droidknights.app.core.designsystem.components.TopAppBar
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource

// TODO Tab 추가
@Composable
fun SessionTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = "세션 목록",
        actions = {
            IconButton(
                onClick = onBackClick,
                modifier = it,
            ) {
                Icon(
                    painter = painterResource(DesignRes.drawable.ic_close),
                    contentDescription = null,
                )
            }
        },
        modifier = modifier,
    )
}
