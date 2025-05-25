package com.droidknights.app.feature.contributor.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.model.ContributorsUiState

@Composable
internal fun ContributorSection(
    section: ContributorsUiState.Contributors.Item.Section,
) {
    Text(
        text = section.title,
        style = KnightsTheme.typography.headlineLargeEB,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 10.dp)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContributorCardPreview() {
    KnightsTheme {
        ContributorSection(
            section = ContributorsUiState.Contributors.Item.Section(
                title = "2025",
            ),
        )
    }
}
