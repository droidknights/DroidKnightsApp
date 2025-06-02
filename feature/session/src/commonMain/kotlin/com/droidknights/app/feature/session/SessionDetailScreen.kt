package com.droidknights.app.feature.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.CircularProgressIndicator
import com.droidknights.app.core.designsystem.components.HorizontalDivider
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.feature.session.components.SessionDetailBookmarkStatePopup
import com.droidknights.app.feature.session.components.SessionDetailChips
import com.droidknights.app.feature.session.components.SessionDetailSpeaker
import com.droidknights.app.feature.session.components.SessionDetailTopAppBar
import com.droidknights.app.feature.session.components.SessionOverview
import com.droidknights.app.feature.session.model.SessionDetailEffect
import com.droidknights.app.feature.session.model.SessionDetailUiState
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SessionDetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.sessionUiEffect.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    var showPopup by remember { mutableStateOf(false) }
    var lastBookmarkState by remember { mutableStateOf(false) }
    var topAppBarHeight by remember { mutableStateOf(0) }

    LaunchedEffect(sessionId) {
        viewModel.fetchSession(sessionId)
    }

    LaunchedEffect(effect) {
        val currentEffect = effect
        if (currentEffect is SessionDetailEffect.ShowToastForBookmarkState) {
            lastBookmarkState = currentEffect.bookmarked
            showPopup = true
            delay(1000L)
            showPopup = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(scrollState),
        ) {
            SessionDetailTopAppBar(
                bookmarked = (uiState as? SessionDetailUiState.Success)?.bookmarked == true,
                onClickBookmark = { viewModel.toggleBookmark() },
                onBackClick = onBackClick,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    topAppBarHeight = coordinates.size.height
                },
            )

            when (val state = uiState) {
                is SessionDetailUiState.Loading -> SessionDetailLoading()
                is SessionDetailUiState.Success -> SessionDetailContent(state.session)
            }
        }

        AnimatedVisibility(
            visible = showPopup,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset {
                    IntOffset(
                        x = 0,
                        y = topAppBarHeight,
                    )
                },
        ) {
            SessionDetailBookmarkStatePopup(bookmarked = lastBookmarkState)
        }
    }
}

@Composable
private fun SessionDetailLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SessionDetailContent(session: Session) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(end = 58.dp),
            text = session.title,
            style = KnightsTheme.typography.headlineMediumB,
            color = KnightsTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(12.dp))

        SessionDetailChips(session = session)

        if (session.content.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SessionOverview(content = session.content)
        }

        Spacer(modifier = Modifier.height(40.dp))

        HorizontalDivider(thickness = 1.dp, color = KnightsTheme.colorScheme.borderColor)

        Spacer(modifier = Modifier.height(40.dp))

        session.speakers.forEach { speaker ->
            SessionDetailSpeaker(speaker)
            if (speaker != session.speakers.last()) {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
