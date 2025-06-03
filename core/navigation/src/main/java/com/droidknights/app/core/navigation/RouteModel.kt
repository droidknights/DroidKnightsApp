package com.droidknights.app.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data class SessionDetail(val sessionId: String) : Route

    @Serializable
    data class SessionList(val sessionId: String? = null) : Route
}

sealed interface MainTabRoute : Route {

    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute

    @Serializable
    data object Bookmark : MainTabRoute
}
