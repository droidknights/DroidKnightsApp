package com.droidknights.app.core.router.internal.navigator

import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.router.api.model.Route
import kotlinx.coroutines.channels.Channel

internal class NavigatorImpl : Navigator, InternalNavigator {

    override val channel = Channel<InternalRoute>(Channel.BUFFERED)

    override suspend fun navigate(route: Route) {
        channel.send(
            InternalRoute.Navigate(
                route = route,
            )
        )
    }

    override suspend fun navigateBack() {
        channel.send(InternalRoute.NavigateBack)
    }
}
