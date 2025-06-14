package com.droidknights.app.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.feature.home.component.ContributorCard
import com.droidknights.app.feature.home.component.SessionCard
import com.droidknights.app.feature.home.component.SponsorCard
import com.droidknights.app.feature.home.model.SponsorsUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val sponsorsUiState by viewModel.sponsorsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }
    HomeScreen(
        padding = padding,
        sponsorsUiState = sponsorsUiState,
        onSessionClick = viewModel::navigateSession,
        onContributorClick = viewModel::navigateContributor,
        onOrganizationSponsorClick = viewModel::navigateOrganizationSponsor,
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    sponsorsUiState: SponsorsUiState,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onOrganizationSponsorClick: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .padding(padding)
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SessionCard(onClick = onSessionClick)
        ContributorCard(onClick = onContributorClick)
        SponsorCard(
            uiState = sponsorsUiState,
            onOrganizationSponsorClick = onOrganizationSponsorClick
        )
    }
}

@Preview
@Composable
private fun PreviewHomeScreen(@PreviewParameter(SponsorsUiStatePreviewParameterProvider::class) sponsorsUiState: SponsorsUiState) {
    HomeScreen(
        padding = PaddingValues(),
        sponsorsUiState = sponsorsUiState,
        onSessionClick = {},
        onContributorClick = {},
        onOrganizationSponsorClick = {}
    )
}
