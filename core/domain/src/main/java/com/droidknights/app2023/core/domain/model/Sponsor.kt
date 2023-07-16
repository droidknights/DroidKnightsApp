package com.droidknights.app2023.core.domain.model

data class Sponsor(
    val name: String,
    val imageUrl: String,
    val homepage: String,
    val grade: Grade,
) {
    enum class Grade { PLATINUM, GOLD, }
}
