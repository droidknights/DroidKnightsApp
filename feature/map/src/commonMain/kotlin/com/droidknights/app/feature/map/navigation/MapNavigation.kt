package com.droidknights.app.feature.map.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.map.MapScreen
import com.droidknights.app.core.navigation.Route.Map as MapRoute

fun NavController.navigateMap() {
    navigate(MapRoute)
}

fun NavGraphBuilder.mapNavGraph() {
    composable<MapRoute> {
        MapScreen()
    }
}
