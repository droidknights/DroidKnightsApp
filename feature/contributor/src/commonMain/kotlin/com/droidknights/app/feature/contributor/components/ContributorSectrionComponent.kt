package com.droidknights.app.feature.contributor.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.model.ContributorsUiState

@Composable
internal fun ContributorSection(
    section: ContributorsUiState.Contributors.Item.Section,
) {
    Text(
        text = section.title,
        style = KnightsTheme.typography.headlineLargeEB,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 10.dp)
    )
}
