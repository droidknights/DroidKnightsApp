package com.droidknights.app.core.data.session.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class RoomResponse {
    ETC,

    @SerialName("Track1")
    TRACK1,

    @SerialName("Track2")
    TRACK2,
}
