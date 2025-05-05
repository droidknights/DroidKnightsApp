package com.droidknights.app

import KnightsTheme
import androidx.compose.runtime.Composable
import com.droidknights.app.feature.main.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    KnightsTheme {
        MainScreen()
    }
}