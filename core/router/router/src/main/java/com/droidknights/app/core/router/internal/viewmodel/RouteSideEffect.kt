package com.droidknights.app.core.router.internal.viewmodel

import com.droidknights.app.core.router.api.model.NavigatorRoute

internal sealed interface RouteSideEffect {

    data class MoveNavigation(
        val route: NavigatorRoute,
    ) : RouteSideEffect

    data object MoveNavigationBack : RouteSideEffect
}
