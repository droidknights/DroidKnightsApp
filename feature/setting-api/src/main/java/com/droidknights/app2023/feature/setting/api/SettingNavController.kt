package com.droidknights.app2023.feature.setting.api

import androidx.navigation.NavOptions
import com.droidknights.app2023.feature.nav.DroidKnightsNavController

interface SettingNavController : DroidKnightsNavController<SettingNavControllerInfo>

data class SettingNavControllerInfo(
    val navOptions: NavOptions,
)
