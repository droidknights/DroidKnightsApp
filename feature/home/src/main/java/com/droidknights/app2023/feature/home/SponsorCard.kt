package com.droidknights.app2023.feature.home

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun SponsorCard() {
    KnightsCard(
        modifier = Modifier
            .height(164.dp),
    ) {
    }
}

@Preview
@Composable
private fun SponsorCardPreview() {
    KnightsTheme {
        SponsorCard()
    }
}
