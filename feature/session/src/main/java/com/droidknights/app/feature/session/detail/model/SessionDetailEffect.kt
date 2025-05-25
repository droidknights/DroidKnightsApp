package com.droidknights.app.feature.session.detail.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface SessionDetailEffect {

    @Immutable
    data object Idle : SessionDetailEffect

    @Immutable
    data class ShowToastForBookmarkState(val bookmarked: Boolean) : SessionDetailEffect
}
