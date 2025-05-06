package com.droidknights.app.core.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Scaffold(
    content: @Composable (PaddingValues) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Scaffold(
        modifier = modifier,
        content = content
    )
}