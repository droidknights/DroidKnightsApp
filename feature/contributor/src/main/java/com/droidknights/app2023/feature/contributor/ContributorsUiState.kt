package com.droidknights.app2023.feature.contributor

import com.droidknights.app2023.core.domain.contributor.Contributor

sealed interface ContributorsUiState {
    object Loading : ContributorsUiState

    data class Contributors(
        val contributors: List<Contributor>,
    ) : ContributorsUiState
}
