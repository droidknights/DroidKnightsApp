package com.droidknights.app.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Contributor : Route

    @Serializable
    data object Session : Route

    @Serializable
    data class SessionDetail(val sessionId: String) : Route

    @Serializable
    data object License : Route

    @Serializable
    data object Map : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute

    @Serializable
    data object Bookmark : MainTabRoute
}
