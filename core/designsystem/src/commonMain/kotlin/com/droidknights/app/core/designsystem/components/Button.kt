package com.droidknights.app.core.designsystem.components

import androidx.compose.runtime.Composable

@Composable
fun Button(
    text: String,
    onClick: () -> Unit
) {
    androidx.compose.material3.Button(
        onClick = onClick
    ) {
        androidx.compose.material3.Text(text)
    }
}