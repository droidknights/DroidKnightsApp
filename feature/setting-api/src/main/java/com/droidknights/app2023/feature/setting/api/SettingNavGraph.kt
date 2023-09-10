package com.droidknights.app2023.feature.setting.api

import androidx.compose.foundation.layout.PaddingValues
import com.droidknights.app2023.feature.nav.DroidKnightsNavGraph

interface SettingNavGraph : DroidKnightsNavGraph<SettingNavGraphInfo>

data class SettingNavGraphInfo(
    val padding: PaddingValues,
    val onChangeDarkTheme: (Boolean) -> Unit,
)
