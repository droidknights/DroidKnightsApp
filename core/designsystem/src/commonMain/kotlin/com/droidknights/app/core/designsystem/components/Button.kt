package com.droidknights.app.core.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier
    ) {
        androidx.compose.material3.Text(text)
    }
}