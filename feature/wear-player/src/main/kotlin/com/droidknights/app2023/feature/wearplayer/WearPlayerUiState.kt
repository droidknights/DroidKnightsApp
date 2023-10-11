package com.droidknights.app2023.feature.wearplayer

import android.net.Uri

sealed interface WearPlayerUiState {

    object Loading : WearPlayerUiState

    data class Success(
        val isPlaying: Boolean,
        val hasPrevious: Boolean,
        val hasNext: Boolean,
        val position: Long,
        val duration: Long,
        val speed: Float,
        val aspectRatio: Float,
        val title: String?,
        val artist: String?,
        val artworkUri: Uri?,
    ) : WearPlayerUiState
}