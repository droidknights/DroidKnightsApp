package com.droidknights.app.core.datastore.settings.di

import com.droidknights.app.core.datastore.settings.DefaultSettingsPreferencesDataSource
import com.droidknights.app.core.datastore.settings.api.SettingsPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SettingsModule {

    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultSettingsPreferencesDataSource,
    ): SettingsPreferencesDataSource
}
