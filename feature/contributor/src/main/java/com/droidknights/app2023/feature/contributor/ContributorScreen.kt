package com.droidknights.app2023.feature.contributor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.domain.model.Contributor

@Composable
fun ContributorRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContributorViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ContributorScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

@Composable
internal fun ContributorScreen(
    uiState: ContributorsUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ActionBarContent(onBackClick)
        when (uiState) {
            ContributorsUiState.Loading -> Unit
            is ContributorsUiState.Contributors ->
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    ContributorTitle()
                    ContributorList(
                        contributors = uiState.contributors,
                    )
                }
        }
    }
}

// TODO: KnightsTopAppBar 사용
@Composable
internal fun ActionBarContent(
    onBackClick: () -> Unit,
) {
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
                .align(Alignment.CenterEnd)
                .clickable { onBackClick() },
        )
    }
}

@Composable
internal fun ContributorTitle() {
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
internal fun ContributorList(contributors: List<Contributor>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(contributors.size) { index ->
            val contributor = contributors[index]
            ContributorItem(contributor = contributor)
        }
    }
}

@Composable
internal fun ContributorItem(contributor: Contributor) {
    Text(
        text = contributor.name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF5E5E5E),
    )
}

@Preview
@Composable
private fun ContributorScreenPreview() {
    ContributorScreen(
        uiState = ContributorsUiState.Contributors(
            listOf(
                Contributor("Contributor1", "https://avatars.githubusercontent.com/u/25101514"),
                Contributor("Contributor2", "https://avatars.githubusercontent.com/u/25101514"),
            )
        ),
        onBackClick = {},
    )
}
