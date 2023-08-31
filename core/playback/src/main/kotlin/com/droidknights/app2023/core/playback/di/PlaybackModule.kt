package com.droidknights.app2023.core.playback.di

import android.app.Service
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.dash.DashMediaSource
import androidx.media3.exoplayer.dash.DefaultDashChunkSource
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter
import androidx.media3.session.MediaLibraryService
import com.droidknights.app2023.core.playback.playstate.PlaybackStateListener
import com.droidknights.app2023.core.playback.session.LibrarySessionCallback
import com.droidknights.app2023.core.playback.session.PlaybackService
import com.droidknights.app2023.core.playback.session.SessionActivityIntentProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@UnstableApi @Module
@InstallIn(ServiceComponent::class)
internal object PlaybackModule {

    @Provides
    @ServiceScoped
    fun player(
        service: Service,
        playbackStateListener: PlaybackStateListener,
    ) : Player {
        val dataSourceFactory = DefaultDataSource.Factory(service)
        val mediaSourceFactory = DashMediaSource.Factory(
            DefaultDashChunkSource.Factory(
                dataSourceFactory.setTransferListener(
                    DefaultBandwidthMeter.Builder(service).build()
                )
            ),
            dataSourceFactory
        )
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
        val renderersFactory = DefaultRenderersFactory(service)
            .forceEnableMediaCodecAsynchronousQueueing()
        return ExoPlayer.Builder(service, renderersFactory, mediaSourceFactory)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()
            .also { player ->
                playbackStateListener.attachTo(player)
            }
    }

    @Provides
    @ServiceScoped
    fun scope(): CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @Provides
    @ServiceScoped
    fun session(
        service: Service,
        player: Player,
        callback: LibrarySessionCallback,
        sessionActivityIntentProvider: SessionActivityIntentProvider
    ): MediaLibraryService.MediaLibrarySession {
        return MediaLibraryService.MediaLibrarySession.Builder(service as PlaybackService, player, callback)
            .apply {
                val pendingIntent = sessionActivityIntentProvider.toPlayer()
                if (pendingIntent != null) {
                    setSessionActivity(pendingIntent)
                }
            }
            .build()
    }
}