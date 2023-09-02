package com.droidknights.app2023.core.playback

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.playback.session.MediaId
import com.droidknights.app2023.core.playback.session.MediaItemProvider
import com.droidknights.app2023.core.playback.session.PlaybackService
import com.droidknights.app2023.core.playback.session.toMediaIdOrNull
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.guava.asDeferred
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerController @Inject constructor(
  private val context: Context,
  private val sessionRepository: SessionRepository,
  private val mediaItemProvider: MediaItemProvider,
) {

  private var _controller: Deferred<MediaController> = newControllerAsync()

  private fun newControllerAsync() = MediaController
    .Builder(context, SessionToken(context, ComponentName(context, PlaybackService::class.java)))
    .buildAsync()
    .asDeferred()

  @OptIn(ExperimentalCoroutinesApi::class)
  private val controller: Deferred<MediaController>
    get() {
      if (_controller.isCompleted) {
        val completedController = _controller.getCompleted()
        if (!completedController.isConnected) {
          completedController.release()
          _controller = newControllerAsync()
        }
      }
      return _controller
    }
  private val scope = CoroutineScope(Dispatchers.Main.immediate)

  fun setPosition(positionMs: Long) = executeAfterPrepare { controller ->
    controller.seekTo(positionMs)
  }

  fun fastForward() = executeAfterPrepare { controller ->
    controller.seekForward()
  }

  fun rewind() = executeAfterPrepare { controller ->
    controller.seekBack()
  }

  fun previous() = executeAfterPrepare { controller ->
    controller.seekToPrevious()
  }

  fun next() = executeAfterPrepare { controller ->
    controller.seekToNext()
  }

  fun play() = executeAfterPrepare { controller ->
    controller.play()
  }

  fun playPause() = executeAfterPrepare { controller ->
    if (controller.isPlaying) {
      controller.pause()
    } else {
      controller.play()
    }
  }

  fun setSpeed(speed: Float) = executeAfterPrepare { controller ->
    controller.setPlaybackSpeed(speed)
  }

  private suspend fun maybePrepare(controller: MediaController): Boolean {
    val sessionId = sessionRepository.getCurrentPlayingSessionId().first() ?: return false
    if (controller.currentSessionId() == sessionId &&
      controller.playbackState in listOf(Player.STATE_READY, Player.STATE_BUFFERING)
    ) {
      return true
    }
    val session = runCatching { sessionRepository.getSession(sessionId) }.getOrNull() ?: return false
    controller.setMediaItem(mediaItemProvider.mediaItem(session))
    controller.prepare()
    return true
  }

  private fun MediaController.currentSessionId(): String? =
    (currentMediaItem?.mediaId?.toMediaIdOrNull() as? MediaId.Session)?.id

  private inline fun executeAfterPrepare(crossinline action: suspend (MediaController) -> Unit) {
    scope.launch {
      val controller = awaitConnect() ?: return@launch
      if (maybePrepare(controller)) {
        action(controller)
      }
    }
  }

  suspend fun awaitConnect(): MediaController? {
    return try {
      controller.await()
    } catch (e: Exception) {
      if (e is CancellationException) throw e
      null
    }
  }
}
