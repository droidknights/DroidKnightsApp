package com.droidknights.app.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Scaffold
import com.droidknights.app.feature.main.components.MainNavHost

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                modifier = Modifier.padding(padding)
            )
        }
    )
}