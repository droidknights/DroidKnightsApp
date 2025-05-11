package com.droidknights.app.feature.home.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.droidknights.app.core.model.sponsor.Sponsor
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Stable
sealed interface SponsorsUiState {

    @Immutable
    data object Loading : SponsorsUiState

    @Immutable
    data object Empty : SponsorsUiState

    @Immutable
    data class Sponsors(
        val sponsors: ImmutableList<Sponsor>,
    ) : SponsorsUiState {

        val platinumCount: Int =
            sponsors.count { it.grade == Sponsor.Grade.PLATINUM }

        val goldCount: Int =
            sponsors.count { it.grade == Sponsor.Grade.GOLD }

        val silverCount: Int =
            sponsors.count { it.grade == Sponsor.Grade.SILVER }

        val groupedSponsorsByGrade: ImmutableList<List<Sponsor>> =
            sponsors
                .groupBy { it.grade }
                .toSortedMap(compareBy { it.priority })
                .values
                .toPersistentList()
    }
}
