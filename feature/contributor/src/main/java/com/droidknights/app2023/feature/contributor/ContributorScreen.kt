package com.droidknights.app2023.feature.contributor

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.component.BottomLogo
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.component.TextChip
import com.droidknights.app2023.core.designsystem.res.rememberPainterResource
import com.droidknights.app2023.core.designsystem.theme.Black
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LocalDarkTheme
import com.droidknights.app2023.core.designsystem.theme.Neon01
import com.droidknights.app2023.core.designsystem.theme.Neon05
import com.droidknights.app2023.core.designsystem.theme.surfaceDim
import com.droidknights.app2023.core.model.Contributor

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
    val lazyListState = rememberLazyListState()
    Box(
        modifier = modifier.navigationBarsPadding(),
    ) {
        when (uiState) {
            ContributorsUiState.Loading -> ContributorList(
                lazyListState = lazyListState,
                contributors = emptyList(),
            )
            is ContributorsUiState.Contributors ->
                ContributorList(
                    lazyListState = lazyListState,
                    contributors = uiState.contributors,
                )
        }
        ContributorTopAppBar(lazyListState, onBackClick)
    }
}

@Composable
private fun ContributorTopAppBar(
    lazyListState: LazyListState,
    onBackClick: () -> Unit,
) {
    val isAtTop by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 }
    }
    val alpha by animateFloatAsState(
        targetValue = if (isAtTop) 0f else 0.8f,
        label = "topAppBarContainerColor",
    )
    val containerColor = MaterialTheme.colorScheme.surfaceDim.copy(alpha)
    KnightsTopAppBar(
        titleRes = R.string.contributor_top_title,
        navigationIconContentDescription = null,
        modifier = Modifier.statusBarsPadding(),
        onNavigationClick = onBackClick,
        containerColor = containerColor,
    )
}

@Composable
private fun TopBanner(darkTheme: Boolean = LocalDarkTheme.current) {
    Box(
        modifier = Modifier
            .background(if (darkTheme) Black else Neon05)
            .statusBarsPadding()
            .padding(top = 48.dp)
    ) {
        Image(
            painter = painterResource(
                id = if (darkTheme) {
                    R.drawable.bg_contributors_darkmode
                } else {
                    R.drawable.bg_contributors_lightmode
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = stringResource(id = R.string.contributor_banner_title),
                style = KnightsTheme.typography.headlineSmallBL,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = stringResource(id = R.string.contributor_banner_description),
                style = KnightsTheme.typography.titleSmallM140,
                color = Neon01,
                modifier = Modifier.padding(top = 6.dp, start = 3.dp),
            )
        }
    }
}

@Composable
private fun ContributorList(
    lazyListState: LazyListState,
    contributors: List<Contributor>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            TopBanner()
        }
        items(contributors.size) { index ->
            val contributor = contributors[index]
            ContributorItem(
                contributor = contributor,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
        item {
            Footer(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Composable
private fun ContributorItem(
    contributor: Contributor,
    modifier: Modifier = Modifier,
) {
    val placeholder = rememberPainterResource(
        lightId = R.drawable.ic_contributor_placeholder_lightmode,
        darkId = R.drawable.ic_contributor_placeholder_darkmode,
    )
    KnightsCard(modifier = modifier) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 24.dp,
                        end = 16.dp
                    )
            ) {
                TextChip(
                    stringResource(id = R.string.contributor_chip),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Text(
                    text = contributor.name,
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            NetworkImage(
                imageUrl = contributor.imageUrl,
                placeholder = placeholder,
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun Footer(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        BottomLogo()
    }
}

@Preview
@Composable
private fun ContributorScreenPreview() {
    ContributorScreen(
        uiState = ContributorsUiState.Contributors(
            listOf(
                Contributor(
                    "Contributor1",
                    "https://avatars.githubusercontent.com/u/25101514"
                ),
                Contributor(
                    "Contributor2",
                    "https://avatars.githubusercontent.com/u/25101514"
                ),
            )
        ),
        onBackClick = {},
    )
}
