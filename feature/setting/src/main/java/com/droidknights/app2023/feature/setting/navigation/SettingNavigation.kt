package com.droidknights.app2023.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.setting.SettingScreen

fun NavController.navigateSetting(navOptions: NavOptions) {
    navigate(SettingRoute.route, navOptions)
}

fun NavGraphBuilder.settingNavGraph() {
    composable(route = SettingRoute.route) {
        SettingScreen()
    }
}

object SettingRoute {
    const val route = "setting"
} 
