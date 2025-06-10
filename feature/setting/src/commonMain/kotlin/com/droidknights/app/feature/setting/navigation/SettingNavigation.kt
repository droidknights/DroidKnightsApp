package com.droidknights.app.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.setting.SettingScreen

fun NavController.navigateSetting(navOptions: NavOptions) {
    navigate(MainTabRoute.Setting, navOptions)
}

fun NavGraphBuilder.settingNavGraph(onLicenseClick: () -> Unit) {
    composable<MainTabRoute.Setting> {
        SettingScreen(onLicenseClick)
    }
}
