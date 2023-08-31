package com.droidknights.app2023.feature.player

import android.content.ComponentName
import android.view.SurfaceView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.droidknights.app2023.core.playback.session.PlaybackService
import kotlinx.coroutines.guava.await

@Composable
fun PlayerView(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var player: Player? by remember { mutableStateOf(null) }
    var surfaceView: SurfaceView? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        player = MediaController
            .Builder(
                context,
                SessionToken(context, ComponentName(context, PlaybackService::class.java))
            )
            .buildAsync()
            .await()
    }

    DisposableEffect(Unit) {
        onDispose {
            player?.clearVideoSurfaceView(surfaceView)
        }
    }

    AndroidView(
        factory = {
            SurfaceView(it).apply {
                surfaceView = this
            }
        },
        update = {
            if (player?.isCommandAvailable(Player.COMMAND_SET_VIDEO_SURFACE) == true) {
                player?.setVideoSurfaceView(it)
            }
        },
        modifier = modifier
    )
}
