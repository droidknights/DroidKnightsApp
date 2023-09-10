package com.droidknights.app2023.feature.setting.navigation

import com.droidknights.app2023.feature.nav.DroidKnightsTab
import com.droidknights.app2023.feature.setting.R
import javax.inject.Inject

internal class SettingTab @Inject constructor() : DroidKnightsTab {
    override val iconResId: Int = R.drawable.ic_setting
    override val contentDescription: String = "설정"
    override val route: String = SettingRoute.route
    override val order: Int = 0
    override val isStartDestination: Boolean = false
}
