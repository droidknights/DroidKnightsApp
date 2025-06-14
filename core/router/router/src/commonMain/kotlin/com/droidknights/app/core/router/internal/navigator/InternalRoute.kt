package com.droidknights.app.core.router.internal.navigator

import com.droidknights.app.core.router.api.model.Route

internal sealed interface InternalRoute {

    data class Navigate(
        val route: Route,
    ) : InternalRoute

    data object NavigateBack : InternalRoute
}
