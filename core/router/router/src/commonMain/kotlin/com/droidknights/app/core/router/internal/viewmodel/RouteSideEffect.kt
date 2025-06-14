package com.droidknights.app.core.router.internal.viewmodel

import com.droidknights.app.core.router.api.model.Route

internal sealed interface RouteSideEffect {

    data class Navigate(
        val route: Route,
    ) : RouteSideEffect

    data object NavigateBack : RouteSideEffect
}
