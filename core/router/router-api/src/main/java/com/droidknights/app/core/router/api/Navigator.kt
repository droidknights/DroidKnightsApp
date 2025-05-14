package com.droidknights.app.core.router.api

interface Navigator {

    fun move(route: Route)

    fun moveBack()
}
