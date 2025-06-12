package com.droidknights.app.core.router.api

import com.droidknights.app.core.router.api.model.Route

interface Navigator {

    suspend fun navigate(route: Route)

    suspend fun navigateWeb(url: String)

    suspend fun navigateBack()
}
