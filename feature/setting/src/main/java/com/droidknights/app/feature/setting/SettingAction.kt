package com.droidknights.app.feature.setting

import com.droidknights.app.core.action.api.Action

internal sealed interface SettingAction : Action {

    data class ChangeDarkTheme(val isDarkTheme: Boolean) : SettingAction
}
