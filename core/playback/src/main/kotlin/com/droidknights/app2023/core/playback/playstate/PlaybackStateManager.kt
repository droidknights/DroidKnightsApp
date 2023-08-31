package com.droidknights.app2023.core.playback.playstate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PlaybackStateManager @Inject constructor() {
    private val _playbackState = MutableStateFlow(PlaybackState())

    val flow: StateFlow<PlaybackState> get() = _playbackState
    var playbackState: PlaybackState
        set(value) {
            _playbackState.value = value
        }
        get() = _playbackState.value
}