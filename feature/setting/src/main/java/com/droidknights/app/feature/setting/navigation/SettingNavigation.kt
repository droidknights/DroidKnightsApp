package com.droidknights.app.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute.Setting
import com.droidknights.app.feature.setting.SettingScreen

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
    onChangeDarkTheme: (Boolean) -> Unit,
) {
    composable<Setting> {
        SettingScreen(padding, onChangeDarkTheme)
    }
}
