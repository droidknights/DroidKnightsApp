package com.droidknights.app2023.feature.home

import com.droidknights.app2023.core.model.Sponsor

sealed interface SponsorsUiState {
    object Loading : SponsorsUiState

    object Empty : SponsorsUiState

    data class Sponsors(
        val sponsors: List<Sponsor>,
    ) : SponsorsUiState {
        val platinumCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.PLATINUM }

        val goldCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.GOLD }
    }
}
