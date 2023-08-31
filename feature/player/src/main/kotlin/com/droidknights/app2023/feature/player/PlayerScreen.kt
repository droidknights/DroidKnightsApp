package com.droidknights.app2023.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun PlayerScreen(
    onBackClick: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    val playerUiState by viewModel.playerUiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(
        LocalContentColor provides Color.White
    ) {
        PlayerContent(
            uiState = playerUiState,
            onBackClick = onBackClick,
            onPrevButtonClick = viewModel::prev,
            onPlayPauseButtonClick = viewModel::playPause,
            onNextButtonClick = viewModel::next,
            onPositionChange = viewModel::setPosition
        )
    }
}

@Composable
private fun PlayerContent(
    uiState: PlayerUiState,
    onBackClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onPlayPauseButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onPositionChange: (Long) -> Unit,
) {
    when (uiState) {
        is PlayerUiState.Loading -> PlayerLoading()
        is PlayerUiState.Success -> Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            PlayerView(
                modifier = Modifier
                    .align(Alignment.Center)
                    .aspectRatio(uiState.aspectRatio)
            )
            Box(modifier = Modifier.fillMaxSize()) {
                BackButton(
                    onClick = onBackClick,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrevButton(
                        enabled = uiState.hasPrevious,
                        onClick = onPrevButtonClick
                    )
                    PlayPauseButton(
                        isPlaying = uiState.isPlaying,
                        onClick = onPlayPauseButtonClick,
                    )
                    NextButton(
                        enabled = uiState.hasPrevious,
                        onClick = onNextButtonClick
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PositionText(uiState.position)
                    PositionSeekBar(
                        modifier = Modifier.weight(1F),
                        position = uiState.position,
                        duration = uiState.duration,
                        onPositionChange = onPositionChange,
                    )
                    PositionText(uiState.duration)
                }
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
internal fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "플레이어 종료",
        )
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
                contentDescription = "일시정지",
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
internal fun PrevButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    IconButton(enabled = enabled, onClick = onClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = "이전 세션",
        )
    }
}

@Composable
internal fun NextButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    IconButton(enabled = enabled, onClick = onClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.SkipNext,
            contentDescription = "다음 세션",
        )
    }
}

@Composable
internal fun PositionText(amount: Long) {
    Text(
        style = KnightsTheme.typography.labelSmallM,
        text = amount.formatAsDuration()
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
@Composable
internal fun PositionSeekBar(
    modifier: Modifier = Modifier,
    position: Long,
    duration: Long,
    onPositionChange: (Long) -> Unit,
) {
    Slider(
        modifier = modifier,
        value = position.toFloat(),
        onValueChange = {
            onPositionChange(it.toLong())
        },
        valueRange = 0F..duration.toFloat().coerceAtLeast(0F)
    )
}