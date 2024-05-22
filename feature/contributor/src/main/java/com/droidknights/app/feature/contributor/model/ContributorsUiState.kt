package com.droidknights.app.feature.contributor.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.droidknights.app.core.model.Contributor
import kotlinx.collections.immutable.ImmutableList

@Stable
sealed interface ContributorsUiState {

    @Immutable
    data object Loading : ContributorsUiState

    @Immutable
    data class Contributors(
        val contributors: ImmutableList<Contributor>,
    ) : ContributorsUiState
}
