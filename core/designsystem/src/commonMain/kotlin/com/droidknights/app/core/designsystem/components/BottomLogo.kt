package com.droidknights.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray

@Composable
fun BottomLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().height(BottomLogoHeight),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Droid Knights 2025",
            style = KnightsTheme.typography.labelMediumR,
            color = LightGray,
        )
    }
}

internal val BottomLogoHeight = 48.dp
