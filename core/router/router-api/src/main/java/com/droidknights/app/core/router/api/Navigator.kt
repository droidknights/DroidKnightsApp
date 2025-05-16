package com.droidknights.app.core.router.api

import com.droidknights.app.core.router.api.model.NavigatorRoute

interface Navigator {

    fun navigate(navigatorRoute: NavigatorRoute)

    fun back()
}
