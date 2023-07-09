package com.droidknights.app2023.feature.contributor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.domain.contributor.Contributor


@Composable
internal fun ContributorScreen(
    uiState: ContributorsUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ActionBarContent()
        Column(
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Title()
            when (uiState) {
                ContributorsUiState.Loading -> Unit
                is ContributorsUiState.Contributors ->
                    ContributorsTabContent(
                        contributors = uiState.contributors,
                    )
            }
        }
    }
}

@Composable
fun ActionBarContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFEEFFE7))
            .statusBarsPadding()
    ) {
        Text(
            text = stringResource(id = R.string.contributor_top_title),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(14.dp)
                .align(Alignment.Center)
        )
        Icon(
            painter = painterResource(id = R.drawable.close_24px),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterEnd),
        )
    }
}

@Composable
fun Title() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(vertical = 37.dp)
    ) {
        Text(
            text = stringResource(id = R.string.contributor_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Divider(color = Color(0xFF000000), thickness = 2.dp)
    }
}

@Composable
fun ContributorsTabContent(contributors: List<Contributor>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        contributors.forEach { contributor ->
            ContributorItem(contributor = contributor)
        }
    }
}

@Composable
fun ContributorItem(contributor: Contributor) {
    Text(
        text = contributor.name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF5E5E5E),
    )
}
