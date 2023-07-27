package com.droidknights.app2023.feature.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app2023.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
internal fun SessionScreen(
    onBackClick: () -> Unit,
) {
    val lazyListState = rememberLazyListState()
    var selectedTrack: Track by remember { mutableStateOf(Track.Track1) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SessionTopAppBar(
            lazyListState = lazyListState,
            selectedTrack = selectedTrack,
            onTrackSelect = { track ->
                coroutineScope.launch {
                    val scrollPosition = when (track) {
                        Track.Track1 -> 0
                        Track.Track2 -> 3
                        Track.Track3 -> 6
                    }
                    lazyListState.animateScrollToItem(scrollPosition)
                }
            },
            onBackClick = onBackClick,
        )
        SessionContent(
            state = lazyListState,
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 48.dp)
                .fillMaxSize()
        )
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .map { index ->
                when {
                    index < 3 -> 0
                    index < 6 -> 1
                    else -> 2
                }
            }
            .collect { index ->
                selectedTrack = Track.values().find { it.ordinal == index }!!
            }
    }
}

/**
 * TODO: TopAppBar 및 탭 UI 구현
 */
@Composable
private fun SessionTopAppBar(
    lazyListState: LazyListState,
    selectedTrack: Track,
    onTrackSelect: (Track) -> Unit,
    onBackClick: () -> Unit,
) {
    var attachToTop: Boolean by remember { mutableStateOf(false) }
    val enter = fadeIn()
    val exit = fadeOut()

    Box {
        AnimatedVisibility(
            visible = !attachToTop,
            enter = enter,
            exit = exit,
        ) {
            SessionTabRow(
                selectedTrack = selectedTrack,
                tracks = Track.values().toList(),
                onTrackSelect = onTrackSelect,
                modifier = Modifier.statusBarsPadding(),
            )
        }
        AnimatedVisibility(
            visible = attachToTop,
            enter = enter,
            exit = exit,
        ) {
            KnightsTopAppBar(
                titleRes = R.string.session_title,
                navigationType = TopAppBarNavigationType.Close,
                navigationIconContentDescription = null,
                modifier = Modifier.statusBarsPadding(),
                onNavigationClick = onBackClick,
            )
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .map { index -> index == 0 }
            .distinctUntilChanged()
            .collect { attachToTop = it }
    }
}


/**
 * TODO: Session 목록 리스트 및 카드 UI 구현
 */
@Composable
private fun SessionContent(
    state: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        state = state
    ) {
        item {
            Text(text = "Track 01", style = KnightsTheme.typography.titleLargeB)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(72.dp))
            Text(text = "Track 02", style = KnightsTheme.typography.titleLargeB)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(72.dp))
            Text(text = "Track 03", style = KnightsTheme.typography.titleLargeB)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SessionCard()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
