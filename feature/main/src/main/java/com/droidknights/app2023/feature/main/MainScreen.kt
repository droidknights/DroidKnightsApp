package com.droidknights.app2023.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen() {
    Scaffold(
        content = { padding ->
            // TODO: Background color using scheme
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF9F9F9))
                    .padding(padding)
            ) {
            
            }
        },
        bottomBar = {
            // TODO: Composable with Bottom Bar
        },
    )
}
