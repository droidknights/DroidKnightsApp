package com.droidknights.app.core.navigation

import com.droidknights.app.core.router.api.model.Route
import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {

    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute

    @Serializable
    data object Bookmark : MainTabRoute
}
