package com.droidknights.app.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.home.components.HomeContributorCard
import com.droidknights.app.feature.home.components.HomeSessionCard
import com.droidknights.app.feature.home.components.HomeSponsorCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreen(
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        HomeSessionCard(
            onClick = onSessionClick,
            modifier = Modifier.fillMaxWidth(),
        )
        HomeContributorCard(
            onClick = onContributorClick,
            modifier = Modifier.fillMaxWidth(),
        )
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    KnightsTheme {
        HomeScreen(
            onSessionClick = {},
            onContributorClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
