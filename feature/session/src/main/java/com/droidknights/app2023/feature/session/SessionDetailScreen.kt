package com.droidknights.app2023.feature.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app2023.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app2023.core.designsystem.theme.surfaceDim
import com.droidknights.app2023.core.model.Session

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    viewModel: SessionDetailViewModel = hiltViewModel(),
) {
    val sessionUiState by viewModel.sessionUiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding(),
    ) {
        SessionDetailTopAppBar(onBackClick = onBackClick)
        SessionDetailContent(uiState = sessionUiState)
    }
}

@Composable
private fun SessionDetailTopAppBar(
    onBackClick: () -> Unit,
) {
    KnightsTopAppBar(
        titleRes = R.string.session_detail_title,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.Back,
        onNavigationClick = onBackClick,
    )
}

@Composable
private fun SessionDetailContent(uiState: SessionDetailUiState) {
    when (uiState) {
        SessionDetailUiState.Loading -> SessionDetailLoading()
        is SessionDetailUiState.Success -> SessionDetailContent(uiState.session)
    }
}


@Composable
private fun SessionDetailLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SessionDetailContent(session: Session) {
    // TODO
}
