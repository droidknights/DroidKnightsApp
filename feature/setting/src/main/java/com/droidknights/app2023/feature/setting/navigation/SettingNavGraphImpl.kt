package com.droidknights.app2023.feature.setting.navigation

import androidx.navigation.NavGraphBuilder
import com.droidknights.app2023.feature.setting.api.SettingNavGraph
import com.droidknights.app2023.feature.setting.api.SettingNavGraphInfo
import javax.inject.Inject

internal class SettingNavGraphImpl @Inject constructor() : SettingNavGraph {
    override fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: SettingNavGraphInfo) {
        navGraphBuilder.settingNavGraph(
            navInfo.padding,
            navInfo.onChangeDarkTheme
        )
    }
}
