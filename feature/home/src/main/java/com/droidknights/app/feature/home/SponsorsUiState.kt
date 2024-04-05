package com.droidknights.app.feature.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.droidknights.app.core.model.Sponsor

@Stable
sealed interface SponsorsUiState {

    @Immutable
    data object Loading : SponsorsUiState

    @Immutable
    data object Empty : SponsorsUiState

    @Immutable
    data class Sponsors(
        val sponsors: List<Sponsor>,
    ) : SponsorsUiState {

        val platinumCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.PLATINUM }

        val goldCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.GOLD }
    }
}
