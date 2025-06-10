package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme

@Composable
internal fun MobileSponsors(
    title: String,
    sponsors: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = KnightsTheme.typography.titleSmallB,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            sponsors.forEach { sponsor ->
                Text(
                    text = sponsor,
                    style = KnightsTheme.typography.titleMediumB,
                )
            }
            Text(
                text = "후원 감사합니다",
                style = KnightsTheme.typography.titleMediumR,
                color = KnightsTheme.colorScheme.borderColor,
            )
        }
    }
}
