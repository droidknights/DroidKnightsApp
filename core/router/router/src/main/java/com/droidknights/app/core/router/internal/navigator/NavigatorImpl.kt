package com.droidknights.app.core.router.internal.navigator

import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.router.api.model.NavigatorRoute
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@ActivityRetainedScoped
internal class NavigatorImpl @Inject constructor() : Navigator, InternalNavigator {

    override val channel = Channel<NavigatorRoute>(Channel.BUFFERED)

    override fun navigate(navigatorRoute: NavigatorRoute) {
        channel.trySend(navigatorRoute)
    }

    override fun back() {
        channel.trySend(BackRoute)
    }
}
