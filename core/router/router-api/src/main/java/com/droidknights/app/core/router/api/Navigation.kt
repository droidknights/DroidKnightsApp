package com.droidknights.app.core.router.api

import com.droidknights.app.core.router.api.model.Route

interface Navigation {

    suspend fun navigate(route: Route, saveState: Boolean = false)

    suspend fun navigateBack()
}
