package com.droidknights.app2023.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF9F9F9))
                    .padding(padding)
            ) {
            
            }
        },
        bottomBar = {
            MainBottomBar()
        },
    )
}

@Composable
private fun MainBottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .navigationBarsPadding()
            .padding(start = 8.dp, end = 8.dp, bottom = 28.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFDCDCDC), // lightgray
                shape = RoundedCornerShape(size = 28.dp)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(28.dp)),
    ) {
        // TODO: BottomBar Item
    }
}
