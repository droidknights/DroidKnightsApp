package com.droidknights.app2023.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class RoomResponse {
    ETC,

    @SerialName("Track1")
    TRACK1,

    @SerialName("Track2")
    TRACK2,

    @SerialName("Track3")
    TRACK3
}
