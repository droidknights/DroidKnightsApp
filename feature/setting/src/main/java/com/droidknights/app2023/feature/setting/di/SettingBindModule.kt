package com.droidknights.app2023.feature.setting.di

import com.droidknights.app2023.feature.nav.DroidKnightsTab
import com.droidknights.app2023.feature.setting.api.SettingNavController
import com.droidknights.app2023.feature.setting.api.SettingNavGraph
import com.droidknights.app2023.feature.setting.navigation.SettingNavControllerImpl
import com.droidknights.app2023.feature.setting.navigation.SettingNavGraphImpl
import com.droidknights.app2023.feature.setting.navigation.SettingTab
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SettingBindModule {
    @Binds
    abstract fun settingNavControllerImpl(
        dataSource: SettingNavControllerImpl,
    ): SettingNavController

    @Binds
    abstract fun settingNavGraphImpl(
        dataSource: SettingNavGraphImpl,
    ): SettingNavGraph

    @Binds
    @IntoSet
    abstract fun settingTab(
        settingTab: SettingTab,
    ): DroidKnightsTab
}
