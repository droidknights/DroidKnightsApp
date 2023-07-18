package com.droidknights.app2023.core.data.model

data class SponsorEntity(
    val name: String,
    val imageUrl: String,
    val homepage: String,
    val grade: Grade,
) {
    enum class Grade { PLATINUM, GOLD, }
}
