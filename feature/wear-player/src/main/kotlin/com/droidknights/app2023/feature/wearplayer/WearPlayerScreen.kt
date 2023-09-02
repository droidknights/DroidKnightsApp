package com.droidknights.app2023.feature.wearplayer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun WearPlayerScreen(
    viewModel: WearPlayerViewModel = hiltViewModel(),
) {
    val playerUiState by viewModel.playerUiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(
        LocalContentColor provides Color.White
    ) {
        PlayerContent(
            uiState = playerUiState,
            onRewindButtonClick = viewModel::rewind,
            onPlayPauseButtonClick = viewModel::playPause,
            onFastForwardButtonClick = viewModel::fastForward,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PlayerContent(
    uiState: WearPlayerUiState,
    onRewindButtonClick: () -> Unit,
    onPlayPauseButtonClick: () -> Unit,
    onFastForwardButtonClick: () -> Unit,
) {
    when (uiState) {
        is WearPlayerUiState.Loading -> PlayerLoading()
        is WearPlayerUiState.Success -> Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            NetworkImage(
                modifier = Modifier.fillMaxSize(),
                imageUrl = uiState.artworkUri?.toString()
            )
            Column(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6F))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.artist ?: "알 수 없음",
                        modifier = Modifier
                            .fillMaxWidth()
                            .basicMarquee()
                            .padding(horizontal = 16.dp),
                        style = KnightsTheme.typography.titleMediumR,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = uiState.title ?: "제목 없음",
                        modifier = Modifier
                            .fillMaxWidth()
                            .basicMarquee()
                            .padding(horizontal = 16.dp),
                        style = KnightsTheme.typography.titleLargeB,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RewindButton(
                        onClick = onRewindButtonClick
                    )
                    PlayPauseButton(
                        isPlaying = uiState.isPlaying,
                        onClick = onPlayPauseButtonClick,
                    )
                    FastForwardButton(
                        onClick = onFastForwardButtonClick
                    )
                }
                PositionText(uiState.position, uiState.duration)
            }
        }
    }
}

@Composable
private fun PlayerLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun PlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        if (isPlaying) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Filled.Pause,
                contentDescription = "일시 정지",
            )
        } else {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "재생",
            )
        }
    }
}

@Composable
internal fun RewindButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.FastRewind,
            contentDescription = "되감기",
        )
    }
}

@Composable
internal fun FastForwardButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.FastForward,
            contentDescription = "빨리 감기",
        )
    }
}

@Composable
internal fun PositionText(position: Long, duration: Long) {
    Text(
        style = KnightsTheme.typography.bodyMediumR,
        text = "${position.formatAsDuration()} / ${duration.formatAsDuration()}"
    )
}

private fun Long.formatAsDuration(): String {
    val hours = this / 1000 / 3600
    val minutes = ((this / 1000) % 3600) / 60
    val seconds = (this / 1000) % 60

    return when {
        hours > 0 -> String.format("%02d:%02d:%02d", hours, minutes, seconds)
        else -> String.format("%02d:%02d", minutes, seconds)
    }
}