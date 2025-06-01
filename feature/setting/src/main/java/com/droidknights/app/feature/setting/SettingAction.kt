package com.droidknights.app.feature.setting

import com.droidknights.app.core.action.Action

internal sealed interface SettingAction : Action {

    data class ChangeDarkTheme(val isDarkTheme: Boolean) : SettingAction
}
