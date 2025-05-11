package com.droidknights.app.core.model.sponsor

data class Sponsor(
    val name: String,
    val imageUrl: String,
    val homepage: String,
    val grade: Grade,
) {

    enum class Grade(val priority: Int) {
        PLATINUM(0),
        GOLD(1),
        SILVER(2),
    }

    companion object {

        val Default = Sponsor(
            name = "",
            imageUrl = "",
            homepage = "",
            grade = Grade.PLATINUM,
        )
    }
}
