package com.droidknights.app.core.navigation

import com.droidknights.app.core.router.api.model.Route
import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {
    val saveState: Boolean
    val launchSingleTop: Boolean

    @Serializable
    data object Home : MainTabRoute {
        override val saveState: Boolean = true
        override val launchSingleTop: Boolean = true
    }

    @Serializable
    data object Setting : MainTabRoute {
        override val saveState: Boolean = true
        override val launchSingleTop: Boolean = true
    }

    @Serializable
    data object Bookmark : MainTabRoute {
        override val saveState: Boolean = true
        override val launchSingleTop: Boolean = true
    }
}
