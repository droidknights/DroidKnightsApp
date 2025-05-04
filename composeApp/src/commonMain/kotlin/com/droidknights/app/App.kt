package com.droidknights.app

import KnightsTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Text
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    KnightsTheme {
        Column(
            modifier = Modifier.systemBarsPadding()
                .fillMaxSize()
        ) {
            Text("Design System Components - Text")
        }
    }
}