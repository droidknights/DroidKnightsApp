package com.droidknights.app2023.feature.home

import com.droidknights.app2023.core.model.Sponsor

data class SponsorsUiState(val sponsors: List<Sponsor>) {
    val platinumCount: Int
        get() = sponsors.count { it.grade == Sponsor.Grade.PLATINUM }

    val goldCount: Int
        get() = sponsors.count { it.grade == Sponsor.Grade.GOLD }

    fun isNotEmpty() = sponsors.isNotEmpty()

    fun sortedByGrade(): List<Sponsor> {
        return sponsors.sortedBy { it.grade.priority }
    }
}
