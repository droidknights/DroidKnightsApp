package com.droidknights.app.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app.feature.setting.SettingScreen

fun NavController.navigateSetting(navOptions: NavOptions) {
    navigate(SettingRoute.ROUTE_SETTING, navOptions)
}

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
    onChangeDarkTheme: (Boolean) -> Unit
) {
    composable(route = SettingRoute.ROUTE_SETTING) {
        SettingScreen(padding, onChangeDarkTheme)
    }
}

object SettingRoute {

    const val ROUTE_SETTING = "setting"
}
