package com.droidknights.app.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.feature.main.components.MainNavHost

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    MainNavHost(modifier)
}