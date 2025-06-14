package com.droidknights.app.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.setting.SettingScreen
import com.droidknights.app.feature.setting.api.RouteSetting

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
) {
    composable<RouteSetting> {
        SettingScreen(
            padding = padding,
        )
    }
}
