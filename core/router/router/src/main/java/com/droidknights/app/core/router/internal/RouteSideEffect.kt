package com.droidknights.app.core.router.internal

import com.droidknights.app.core.router.api.Route

internal sealed interface RouteSideEffect {

    data class MoveNavigation(
        val route: Route,
    ) : RouteSideEffect

    data object MoveNavigationBack : RouteSideEffect
}
