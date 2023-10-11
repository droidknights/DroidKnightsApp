package com.droidknights.app2023.core.playback.session

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface MediaId {
  @Serializable
  @SerialName("root")
  object Root : MediaId

  @Serializable
  @SerialName("session")
  data class Session(val id: String) : MediaId

  @Serializable
  @SerialName("tag")
  data class Tag(
    val name: String,
  ) : MediaId

  @Serializable
  @SerialName("track")
  sealed interface Track : MediaId {
    @Serializable
    @SerialName("keynote")
    object Keynote : Track

    @Serializable
    @SerialName("track-01")
    object TrackOne : Track

    @Serializable
    @SerialName("track-02")
    object TrackTwo : Track

    @Serializable
    @SerialName("track-03")
    object TrackThree : Track
  }
}