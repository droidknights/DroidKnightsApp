package com.droidknights.app.core.data.setting.di

import com.droidknights.app.core.data.setting.SettingRepositoryImpl
import com.droidknights.app.core.data.setting.api.SettingRepository
import org.koin.dsl.module

val coreDataSettingModule = module {
    single<SettingRepository> { SettingRepositoryImpl(get()) }
}
