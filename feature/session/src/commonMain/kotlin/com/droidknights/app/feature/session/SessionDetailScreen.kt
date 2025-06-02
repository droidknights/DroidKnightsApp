package com.droidknights.app.feature.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.Button
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.feature.session.components.SessionDetailTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SessionDetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var isBookmarked by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    LaunchedEffect(sessionId) {
        viewModel.fetchSession(sessionId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SessionDetailTopAppBar(
            bookmarked = isBookmarked,
            onClickBookmark = { isBookmarked = !isBookmarked },
            onBackClick = onBackClick,
        )
        Text("SessionDetailScreen - $sessionId")
        // TODO session detail 화면
        Text("$uiState")
        Button(text = "Back", onClick = onBackClick)
    }
}
