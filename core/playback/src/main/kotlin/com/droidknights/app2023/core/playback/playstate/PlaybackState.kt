package com.droidknights.app2023.core.playback.playstate

import androidx.media3.common.C

data class PlaybackState(
    val isPlaying: Boolean = false,
    val hasPrevious: Boolean = false,
    val hasNext: Boolean = false,
    val position: Long = C.TIME_UNSET,
    val duration: Long = C.TIME_UNSET,
    val speed: Float = 1F,
    val aspectRatio: Float = 16F / 9F
)
