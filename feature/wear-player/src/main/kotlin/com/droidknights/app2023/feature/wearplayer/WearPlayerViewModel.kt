package com.droidknights.app2023.feature.wearplayer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetCurrentPlayingSessionUseCase
import com.droidknights.app2023.core.domain.usecase.UpdateCurrentPlayingSessionUseCase
import com.droidknights.app2023.core.playback.PlayerController
import com.droidknights.app2023.core.playback.playstate.PlaybackStateManager
import com.droidknights.app2023.feature.wearplayer.navigation.WearPlayerRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WearPlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCurrentPlayingSessionUseCase: GetCurrentPlayingSessionUseCase,
    updateCurrentPlayingSessionUseCase: UpdateCurrentPlayingSessionUseCase,
    private val playbackStateManager: PlaybackStateManager,
    private val playerController: PlayerController,
) : ViewModel() {
    private val _playerUiState =
        MutableStateFlow<WearPlayerUiState>(WearPlayerUiState.Loading)
    val playerUiState: StateFlow<WearPlayerUiState> = _playerUiState

    init {
        viewModelScope.launch {
            val sessionId = savedStateHandle.get<String?>(WearPlayerRoute.argumentName)
                .takeIf { !it.isNullOrBlank() }
                ?: getCurrentPlayingSessionUseCase()?.id
                ?: "1" // 처음부터 재생
            updateCurrentPlayingSessionUseCase(sessionId)
            playerController.play()
        }
        viewModelScope.launch {
            playbackStateManager.flow.collect {
                _playerUiState.value = WearPlayerUiState.Success(
                    it.isPlaying,
                    it.hasPrevious,
                    it.hasNext,
                    it.position,
                    it.duration,
                    it.speed,
                    it.aspectRatio,
                    it.title,
                    it.artist,
                    it.artworkUri,
                )
            }
        }
    }

    fun playPause() {
        playerController.playPause()
    }

    fun rewind() {
        playerController.rewind()
    }

    fun fastForward() {
        playerController.fastForward()
    }
}
