package com.droidknights.app2023.feature.contributor

import androidx.compose.runtime.Stable
import com.droidknights.app2023.core.model.Contributor
import kotlinx.collections.immutable.PersistentList

sealed interface ContributorsUiState {
    object Loading : ContributorsUiState

    @Stable
    data class Contributors(
        val contributors: PersistentList<Contributor>,
    ) : ContributorsUiState
}
