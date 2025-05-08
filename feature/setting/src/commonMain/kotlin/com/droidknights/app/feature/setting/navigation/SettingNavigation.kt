package com.droidknights.app.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.setting.SettingScreen

fun NavController.navigateSetting() {
    navigate(MainTabRoute.Setting)
}

fun NavGraphBuilder.settingNavGraph(
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.Setting> {
        SettingScreen(onBackClick = onBackClick)
    }
}
