package com.droidknights.app2023.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO : SerialName 영어로 변경
 */
@Serializable
internal enum class LevelResponse {
    @SerialName("기타")
    ETC,

    @SerialName("초급")
    BASIC,

    @SerialName("중급")
    INTERMEDIATE,

    @SerialName("고급")
    ADVANCED
}
