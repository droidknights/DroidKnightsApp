package com.droidknights.app2023.feature.setting.navigation

import androidx.navigation.NavController
import com.droidknights.app2023.feature.setting.api.SettingNavController
import com.droidknights.app2023.feature.setting.api.SettingNavControllerInfo
import javax.inject.Inject

internal class SettingNavControllerImpl @Inject constructor() : SettingNavController {
    override fun route(): String = SettingRoute.route
    override fun navigate(navController: NavController, navInfo: SettingNavControllerInfo) {
        navController.navigateSetting(navInfo.navOptions)
    }
}
