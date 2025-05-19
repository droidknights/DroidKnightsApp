package com.droidknights.app.feature.setting.di

import com.droidknights.app.feature.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSettingModule = module {
    viewModelOf(::SettingViewModel)
}
