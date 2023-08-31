package com.droidknights.app2023.feature.player

sealed interface PlayerUiState {

    object Loading : PlayerUiState

    data class Success(
        val isPlaying: Boolean,
        val hasPrevious: Boolean,
        val hasNext: Boolean,
        val position: Long,
        val duration: Long,
        val speed: Float,
        val aspectRatio: Float
    ) : PlayerUiState
}