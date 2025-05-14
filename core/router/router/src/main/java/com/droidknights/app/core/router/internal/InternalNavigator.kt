package com.droidknights.app.core.router.internal

import kotlinx.coroutines.channels.Channel

internal interface InternalNavigator {

    val channel: Channel<RouteSideEffect>
}
