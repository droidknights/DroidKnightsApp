package com.droidknights.app.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlineChip(text: String) {
    TextChip(
        text = text,
        containerColor = Color.Transparent,
        labelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)),
    )
}

@Preview
@Composable
private fun PreviewOutlineChip() {
    OutlineChip(text = "Android")
}
