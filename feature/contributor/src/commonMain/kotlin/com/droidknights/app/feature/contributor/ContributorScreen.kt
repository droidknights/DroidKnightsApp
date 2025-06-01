package com.droidknights.app.feature.contributor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.BottomLogo
import com.droidknights.app.feature.contributor.components.ContributorCard
import com.droidknights.app.feature.contributor.components.ContributorTopAppBar
import com.droidknights.app.feature.contributor.components.ContributorTopBanner
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun ContributorScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContributorViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContributorTopAppBar(onBackClick = onBackClick)
        ContributorList(
            uiState = uiState,
            lazyListState = lazyListState,
        )
    }
}

@Composable
private fun ContributorList(
    uiState: ContributorsUiState,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            ContributorTopBanner()
        }

        when (uiState) {
            is ContributorsUiState.Loading -> {
                items(SHIMMERING_ITEM_COUNT) {
                    ContributorCard(
                        contributor = ContributorsUiState.Contributors.Contributor.Default,
                        showPlaceholder = true,
                        modifier = Modifier.padding(horizontal = 8.dp),
                    )
                }
            }

            is ContributorsUiState.Contributors -> {
                items(uiState.contributors) { item ->
                    ContributorCard(
                        contributor = item,
                        showPlaceholder = false,
                        modifier = Modifier.padding(horizontal = 8.dp),
                    )
                }
            }
        }

        item {
            Box(modifier = Modifier.padding(bottom = 16.dp)) {
                BottomLogo()
            }
        }
    }
}

private const val SHIMMERING_ITEM_COUNT = 4
