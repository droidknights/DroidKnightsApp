package com.droidknights.app2023.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeScreen(padding: PaddingValues, onContributorClick: () -> Unit) {
    Column(
        Modifier
            .background(color = Color(0xFFF9F9F9))
            .padding(padding)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ContributorCard(onClick = onContributorClick)
    }
}
