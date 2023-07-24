package com.droidknights.app2023.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KnightsTopAppBar(
    @StringRes titleRes: Int,
    navigationIconContentDescription: String?,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.Back,
    contentColor: Color = Color(0xFF868686),
    containerColor: Color = Color(0xFFFFFFFF),
    onNavigationClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val icon: @Composable (Modifier, imageVector: ImageVector) -> Unit =
            { modifier, imageVector ->
                IconButton(
                    onClick = onNavigationClick,
                    modifier = modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = navigationIconContentDescription,
                    )
                }
            }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor)
                .then(modifier)
        ) {
            if (navigationType == TopAppBarNavigationType.Back) {
                icon(
                    Modifier.align(Alignment.CenterStart),
                    Icons.Filled.ArrowBack
                )
            }
            if (navigationType == TopAppBarNavigationType.Close) {
                icon(
                    Modifier.align(Alignment.CenterEnd),
                    Icons.Filled.Close
                )
            }
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

enum class TopAppBarNavigationType { Back, Close }

@Preview
@Composable
private fun KnightsTopAppBarPreviewBack() {
    KnightsTopAppBar(
        titleRes = android.R.string.untitled,
        navigationType = TopAppBarNavigationType.Back,
        navigationIconContentDescription = "Navigation icon"
    )
}

@Preview
@Composable
private fun KnightsTopAppBarPreviewClose() {
    KnightsTopAppBar(
        titleRes = android.R.string.untitled,
        navigationType = TopAppBarNavigationType.Close,
        navigationIconContentDescription = "Navigation icon"
    )
}
