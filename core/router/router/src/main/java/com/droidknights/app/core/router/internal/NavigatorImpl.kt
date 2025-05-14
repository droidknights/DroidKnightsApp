package com.droidknights.app.core.router.internal

import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.router.api.Route
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@ActivityRetainedScoped
internal class NavigatorImpl @Inject constructor() : Navigator, InternalNavigator {

    override val channel = Channel<Route>(Channel.BUFFERED)

    override fun move(route: Route) {
        channel.trySend(route)
    }

    override fun moveBack() {
        channel.trySend(RouteBack)
    }
}
