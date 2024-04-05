package com.droidknights.app2023.core.playback.session

import androidx.media3.common.MediaItem
import androidx.media3.session.LibraryResult
import androidx.media3.session.LibraryResult.RESULT_ERROR_NOT_SUPPORTED
import androidx.media3.session.MediaLibraryService.LibraryParams
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSession.ControllerInfo
import com.google.common.collect.ImmutableList
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.guava.future
import javax.inject.Inject

internal class LibrarySessionCallback @Inject constructor(
  private val mediaItemProvider: MediaItemProvider,
  private val scope: CoroutineScope,
) : MediaLibrarySession.Callback {

  override fun onGetLibraryRoot(
    session: MediaLibrarySession,
    browser: ControllerInfo,
    params: LibraryParams?,
  ): ListenableFuture<LibraryResult<MediaItem>> {
    if (params?.isRecent == true) {
      return Futures.immediateFuture(LibraryResult.ofError(RESULT_ERROR_NOT_SUPPORTED))
    }
    return Futures.immediateFuture(LibraryResult.ofItem(mediaItemProvider.root(), params))
  }

  override fun onGetItem(
    session: MediaLibrarySession,
    browser: ControllerInfo,
    mediaId: String,
  ): ListenableFuture<LibraryResult<MediaItem>> = scope.future {
    val item = mediaItemProvider.item(mediaId)
    if (item != null) {
      LibraryResult.ofItem(item, null)
    } else {
      LibraryResult.ofError(LibraryResult.RESULT_ERROR_BAD_VALUE)
    }
  }

  override fun onGetChildren(
    session: MediaLibrarySession,
    browser: ControllerInfo,
    parentId: String,
    page: Int,
    pageSize: Int,
    params: LibraryParams?,
  ): ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> = scope.future {
    val children = mediaItemProvider.children(parentId)
    if (children != null) {
      LibraryResult.ofItemList(children, params)
    } else {
      LibraryResult.ofError(LibraryResult.RESULT_ERROR_BAD_VALUE)
    }
  }

  override fun onAddMediaItems(
    mediaSession: MediaSession,
    controller: ControllerInfo,
    mediaItems: List<MediaItem>
  ): ListenableFuture<List<MediaItem>> = scope.future {
    mediaItems.map { mediaItem ->
      mediaItemProvider.item(mediaItem.mediaId) ?: mediaItem
    }
  }

  override fun onSubscribe(
    session: MediaLibrarySession,
    browser: ControllerInfo,
    parentId: String,
    params: LibraryParams?
  ): ListenableFuture<LibraryResult<Void>> = scope.future {
    val children = mediaItemProvider.children(parentId)
      ?: return@future LibraryResult.ofError(LibraryResult.RESULT_ERROR_BAD_VALUE)
    session.notifyChildrenChanged(browser, parentId, children.size, params)
    LibraryResult.ofVoid()
  }

  override fun onPlaybackResumption(
    mediaSession: MediaSession,
    controller: ControllerInfo
  ): ListenableFuture<MediaSession.MediaItemsWithStartPosition> {
    return scope.future {
      mediaItemProvider.currentMediaItemsOrKeynote()
    }
  }
}
