package com.droidknights.app.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute.Setting
import com.droidknights.app.core.router.api.model.Route
import com.droidknights.app.feature.setting.SettingScreen

fun NavGraphBuilder.settingNavGraph(
    selectedTabRoute: State<Route>,
    padding: PaddingValues,
    onChangeDarkTheme: (Boolean) -> Unit,
) {
    composable<Setting> {
        SettingScreen(
            selectedTabRoute = selectedTabRoute,
            padding = padding,
            onChangeDarkTheme = onChangeDarkTheme,
        )
    }
}
