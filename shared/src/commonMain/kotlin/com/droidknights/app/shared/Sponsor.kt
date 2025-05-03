package com.droidknights.app.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsor(
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("grade")
    val grade: Grade,
) {
    @Serializable
    enum class Grade(val priority: Int) { PLATINUM(0), GOLD(1), SILVER(2) }
}
