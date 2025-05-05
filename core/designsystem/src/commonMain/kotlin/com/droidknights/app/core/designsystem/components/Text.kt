package com.droidknights.app.core.designsystem.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

// TODO m3 Text로 구현을 위임
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle? = null
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier,
        style = style ?: LocalTextStyle.current
    )
}