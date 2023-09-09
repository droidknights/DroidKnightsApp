package com.droidknights.app2023.core.repo.setting.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.droidknights.app2023.core.repo.setting.api.SettingsRepository
import com.droidknights.app2023.core.repo.setting.DefaultSettingsRepository
import com.droidknights.app2023.core.repo.setting.SettingsPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SettingRepoModule {
    private const val SETTING_DATASTORE_NAME = "SETTINGS_PREFERENCES"
    private val Context.settingDataStore by preferencesDataStore(SETTING_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named(SettingsPreferencesDataSource.PreferencesKey.DATA_STORE)
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.settingDataStore

}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SettingRepoBindModule {
    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultSettingsRepository,
    ): SettingsRepository
}
