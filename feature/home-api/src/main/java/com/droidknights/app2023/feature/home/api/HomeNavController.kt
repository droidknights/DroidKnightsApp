package com.droidknights.app2023.feature.home.api

import androidx.navigation.NavOptions
import com.droidknights.app2023.feature.nav.DroidKnightsNavController

interface HomeNavController : DroidKnightsNavController<HomeNavControllerInfo> {
    fun isHomeRoute(currentRoute: String?): Boolean
}

data class HomeNavControllerInfo(
    val navOptions: NavOptions,
)
