package com.droidknights.app2023.core.playback.session

import android.app.Application
import android.net.Uri
import androidx.media3.common.MediaItem
import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.playback.R
import javax.inject.Inject

class MediaItemProvider @Inject constructor(
  private val sessionRepository: SessionRepository,
  private val application: Application,
) {

  fun root(): MediaItem = MediaItem(
    title = application.getString(R.string.media_session_root_title),
    description = application.getString(R.string.media_session_root_description),
    browsable = true,
    isPlayable = false,
    mediaId = MediaId.Root,
  )

  suspend fun item(id: String): MediaItem? {
    val mediaId = id.toMediaIdOrNull() ?: return null
    return when (mediaId) {
      MediaId.Root -> root()
      is MediaId.Session -> { sessionRepository.getSession(mediaId.id).let(::mediaItem) }
      is MediaId.Tag -> { mediaItem(mediaId) }
      is MediaId.Track -> {
        val sessions = sessionRepository.getSessions()
        mediaItem(mediaId, sessions)
      }
    }
  }

  suspend fun children(id: String): List<MediaItem>? {
    val mediaId = id.toMediaIdOrNull() ?: return null
    val sessions = runCatching { sessionRepository.getSessions() }.getOrNull() ?: return null
    return when (mediaId) {
      MediaId.Root -> {
        listOf(
          mediaItem(track = MediaId.Track.Keynote, sessions = sessions),
          mediaItem(track = MediaId.Track.TrackOne, sessions = sessions),
          mediaItem(track = MediaId.Track.TrackTwo, sessions = sessions),
          mediaItem(track = MediaId.Track.TrackThree, sessions = sessions),
        )
      }
      is MediaId.Track.Keynote -> { mediaItems(mediaId, sessions) }
      is MediaId.Track -> { mediaItems(mediaId, sessions) }
      is MediaId.Tag -> { mediaItems(mediaId, sessions) }
      is MediaId.Session -> {
        runCatching { sessionRepository.getSession(mediaId.id) }.getOrNull()
          ?.let { session -> listOf(mediaItem(session)) }
      }
    }
  }

  private fun mediaItem(
    track: MediaId.Track,
    sessions: List<Session>,
  ) = MediaItem(
    title = when (track) {
      is MediaId.Track.Keynote -> application.getString(R.string.media_session_keynote_title)
      is MediaId.Track.TrackOne -> application.getString(R.string.media_session_track_1_title)
      is MediaId.Track.TrackTwo -> application.getString(R.string.media_session_track_2_title)
      is MediaId.Track.TrackThree -> application.getString(R.string.media_session_track_3_title)
    },
    description = when (track) {
      is MediaId.Track.Keynote -> application.getString(R.string.media_session_keynote_description)
      is MediaId.Track.TrackOne -> application.getString(R.string.media_session_track_1_description)
      is MediaId.Track.TrackTwo -> application.getString(R.string.media_session_track_2_description)
      is MediaId.Track.TrackThree -> application.getString(R.string.media_session_track_3_description)
    },
    mediaId = track,
    browsable = true,
    isPlayable = false,
    imageUri = when (track) {
      is MediaId.Track.Keynote -> Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/logo.jpg")
      is MediaId.Track.TrackOne -> Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/track1.jpg")
      is MediaId.Track.TrackTwo -> Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/track2.jpg")
      is MediaId.Track.TrackThree -> Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/track3.jpg")
    },
    artist = room(track).let { room ->
      sessions.filter { session -> session.room == room }
        .mapNotNull { it.speakers.firstOrNull() }
        .joinToString(", ") { it.name }
    },
  )

  private fun mediaItem(
    tag: MediaId.Tag
  ) = MediaItem(
    title = tag.name,
    description = "${tag.name}에 관한 발표 목록입니다.",
    mediaId = tag,
    browsable = true,
    isPlayable = false,
    imageUri = Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/logo.jpg"),
  )

  private fun mediaItems(
    track: MediaId.Track.Keynote,
    sessions: List<Session>,
  ): List<MediaItem> {
    return room(track).let { room ->
      sessions.filter { session -> session.room == room }
        .map(::mediaItem)
    } + sessions
      .flatMap { session -> session.tags }
      .distinctBy { it.name }
      .map { tag -> MediaId.Tag(tag.name) }
      .map { tag -> mediaItem(tag) }
  }

  private fun mediaItems(
    track: MediaId.Track,
    sessions: List<Session>,
  ) = room(track).let { room ->
    sessions.filter { session -> session.room == room }
      .map(::mediaItem)
  }

  private fun mediaItems(
    tag: MediaId.Tag,
    sessions: List<Session>,
  ) = sessions
    .filter { session -> session.tags.any { it.name == tag.name } }
    .map(::mediaItem)


  fun mediaItem(session: Session): MediaItem = MediaItem(
    title = session.title,
    description = session.content,
    mediaId = MediaId.Session(session.id),
    browsable = false,
    isPlayable = true,
    sourceUri = session.video.manifestUrl.takeIf { it.isNotBlank() }?.let(Uri::parse),
    imageUri = session.speakers.firstOrNull()
      ?.imageUrl.takeIf { !it.isNullOrBlank() }
      ?.let(Uri::parse)
      ?: Uri.parse("https://raw.githubusercontent.com/workspace/media-samples/main/img/logo.jpg"),
    artist = session.speakers.joinToString(",") { it.name },
  )

  private fun room(
    track: MediaId.Track
  ) = when (track) {
    is MediaId.Track.Keynote -> Room.ETC
    is MediaId.Track.TrackOne -> Room.TRACK1
    is MediaId.Track.TrackTwo -> Room.TRACK2
    is MediaId.Track.TrackThree -> Room.TRACK3
  }
}
