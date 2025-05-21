package com.droidknights.app.core.router.internal.navigator

import com.droidknights.app.core.router.api.Navigation
import com.droidknights.app.core.router.api.model.Route
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@ActivityRetainedScoped
internal class NavigationImpl @Inject constructor() : Navigation, InternalNavigator {

    override val channel = Channel<InternalRoute>(Channel.BUFFERED)

    override suspend fun navigate(route: Route, saveState: Boolean) {
        channel.send(InternalRoute.Navigate(route = route, saveState = saveState))
    }

    override suspend fun navigateBack() {
        channel.send(InternalRoute.NavigateBack)
    }
}
