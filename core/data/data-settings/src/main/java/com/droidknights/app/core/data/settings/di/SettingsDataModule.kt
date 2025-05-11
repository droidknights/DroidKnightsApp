package com.droidknights.app.core.data.settings.di

import com.droidknights.app.core.data.settings.SettingsRepositoryImpl
import com.droidknights.app.core.data.settings.api.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SettingsDataModule {

    @Binds
    abstract fun bindsSettingsRepository(
        repository: SettingsRepositoryImpl,
    ): SettingsRepository
}
