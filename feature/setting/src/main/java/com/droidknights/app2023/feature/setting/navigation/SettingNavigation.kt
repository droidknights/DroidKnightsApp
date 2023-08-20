package com.droidknights.app2023.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.setting.SettingScreen

fun NavController.navigateSetting(navOptions: NavOptions) {
    navigate(SettingRoute.route, navOptions)
}

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
    onChangeDarkTheme: (Boolean) -> Unit
) {
    composable(route = SettingRoute.route) {
        SettingScreen(padding, onChangeDarkTheme)
    }
}

object SettingRoute {
    const val route = "setting"
}
