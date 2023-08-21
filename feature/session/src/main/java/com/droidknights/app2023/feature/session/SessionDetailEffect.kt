package com.droidknights.app2023.feature.session

sealed interface SessionDetailEffect {
    object Idle : SessionDetailEffect
    data class ShowToastForBookmarkState(val bookmarked: Boolean) : SessionDetailEffect
}
