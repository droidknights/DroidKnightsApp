package com.droidknights.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.contentColorFor
import droidknights.core.designsystem.generated.resources.Res
import droidknights.core.designsystem.generated.resources.ic_arrow_back
import droidknights.core.designsystem.generated.resources.ic_close
import droidknights.core.designsystem.generated.resources.ic_session_bookmark_filled
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = KnightsTheme.colorScheme.background,
    navigation: (@Composable (Modifier) -> Unit)? = null,
    actions: (@Composable (Modifier) -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        color = backgroundColor,
        contentColor = contentColorFor(backgroundColor).copy(alpha = 0.5F),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            navigation?.invoke(Modifier.align(Alignment.CenterStart))
            Text(
                text = title,
                style = KnightsTheme.typography.titleSmallM,
                modifier = Modifier.align(Alignment.Center),
            )
            actions?.invoke(Modifier.align(Alignment.CenterEnd))
        }
    }
}

@Preview
@Composable
private fun KnightsTopAppBarPreviewClose() {
    KnightsTheme {
        TopAppBar(
            title = "세션 목록",
            actions = { modifier ->
                IconButton(
                    onClick = {},
                    modifier = modifier,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_close),
                        contentDescription = null,
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun KnightsTopAppBarPreviewBack() {
    KnightsTheme {
        TopAppBar(
            title = "세션 상세 정보",
            navigation = {
                IconButton(
                    onClick = {},
                    modifier = it,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = null,
                    )
                }
            },
            actions = { modifier ->
                IconButton(
                    onClick = {},
                    modifier = modifier,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_session_bookmark_filled),
                        contentDescription = null,
                        tint = KnightsTheme.colorScheme.primary,
                    )
                }
            },
        )
    }
}
