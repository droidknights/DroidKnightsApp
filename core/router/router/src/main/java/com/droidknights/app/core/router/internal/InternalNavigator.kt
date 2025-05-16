package com.droidknights.app.core.router.internal

import com.droidknights.app.core.router.api.model.NavigatorRoute
import kotlinx.coroutines.channels.Channel

internal interface InternalNavigator {

    val channel: Channel<NavigatorRoute>
}
