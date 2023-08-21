package com.droidknights.app2023.feature.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.droidknights.app2023.core.model.Sponsor

sealed interface SponsorsUiState {
    object Loading : SponsorsUiState

    object Empty: SponsorsUiState

    data class Sponsors(
        val sponsors: List<Sponsor>,
    ) : SponsorsUiState {
        val platinumCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.PLATINUM }

        val goldCount: Int
            get() = sponsors.count { it.grade == Sponsor.Grade.GOLD }
    }
}

internal class SponsorsUiStatePreviewParameterProvider : PreviewParameterProvider<SponsorsUiState> {
    override val values: Sequence<SponsorsUiState> = sequenceOf(
        SponsorsUiState.Sponsors(
            sponsors = listOf(
                Sponsor(
                    name = "Sponsor1",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.GOLD,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
                Sponsor(
                    name = "Sponsor2",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.PLATINUM,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
            )
        ),
        SponsorsUiState.Loading,
    )
}
