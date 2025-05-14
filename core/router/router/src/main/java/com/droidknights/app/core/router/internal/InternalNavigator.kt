package com.droidknights.app.core.router.internal

import com.droidknights.app.core.router.api.Route
import kotlinx.coroutines.channels.Channel

internal interface InternalNavigator {

    val channel: Channel<Route>
}
