package com.droidknights.app2023.feature.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetCurrentPlayingSessionUseCase
import com.droidknights.app2023.core.domain.usecase.UpdateCurrentPlayingSessionUseCase
import com.droidknights.app2023.core.playback.PlayerController
import com.droidknights.app2023.core.playback.playstate.PlaybackStateManager
import com.droidknights.app2023.feature.player.navigation.PlayerRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCurrentPlayingSessionUseCase: GetCurrentPlayingSessionUseCase,
    updateCurrentPlayingSessionUseCase: UpdateCurrentPlayingSessionUseCase,
    private val playbackStateManager: PlaybackStateManager,
    private val playerController: PlayerController,
) : ViewModel() {
    private val _playerUiState =
        MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val playerUiState: StateFlow<PlayerUiState> = _playerUiState

    init {
        viewModelScope.launch {
            val sessionId = savedStateHandle.get<String?>(PlayerRoute.argumentName)
                .takeIf { !it.isNullOrBlank() }
                ?: getCurrentPlayingSessionUseCase()?.id
                ?: "1" // 처음부터 재생
            updateCurrentPlayingSessionUseCase(sessionId)
            playerController.play()
        }
        viewModelScope.launch {
            playbackStateManager.flow.collect {
                _playerUiState.value = PlayerUiState.Success(
                    it.isPlaying,
                    it.hasPrevious,
                    it.hasNext,
                    it.position,
                    it.duration,
                    it.speed,
                    it.aspectRatio
                )
            }
        }
    }

    fun playPause() {
        playerController.playPause()
    }

    fun prev() {
        playerController.previous()
    }

    fun next() {
        playerController.next()
    }

    fun setPosition(position: Long) {
        playerController.setPosition(position)
    }
}
