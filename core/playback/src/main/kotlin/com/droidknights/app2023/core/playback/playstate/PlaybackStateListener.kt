package com.droidknights.app2023.core.playback.playstate

import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

internal class PlaybackStateListener @Inject constructor(
    private val scope: CoroutineScope,
    private val playbackStateManager: PlaybackStateManager
) : Player.Listener {

    private lateinit var player: Player

    fun attachTo(player: Player) {
        this.player = player
        player.addListener(this)

        scope.launch {
            playbackStateManager.flow
                .map { it.isPlaying }
                .collectLatest { isPlaying ->
                    if (isPlaying) {
                        while (true) {
                            updatePlayState()
                            delay(400.milliseconds)
                        }
                    }
                }
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        updatePlayState()
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        updatePlayState()
    }

    override fun onPositionDiscontinuity(
        oldPosition: Player.PositionInfo,
        newPosition: Player.PositionInfo,
        reason: Int
    ) {
        updatePlayState()
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        updatePlayState()
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
        updatePlayState()
    }

    override fun onVideoSizeChanged(videoSize: VideoSize) {
        updatePlayState()
    }

    private fun updatePlayState() {
        val playbackState = player.playbackState
        playbackStateManager.playbackState = PlaybackState(
            isPlaying = when {
                playbackState == Player.STATE_ENDED || playbackState == Player.STATE_IDLE -> false
                player.playWhenReady -> true
                else -> false
            },
            hasPrevious = player.currentMediaItemIndex == 0 && player.hasPreviousMediaItem(),
            hasNext = player.hasNextMediaItem(),
            position = player.contentPosition,
            duration = player.duration,
            speed = player.playbackParameters.speed,
            aspectRatio = with(player.videoSize) {
                if (height == 0 || width == 0) 16F / 9F else width * pixelWidthHeightRatio / height
            }
        )
    }
}