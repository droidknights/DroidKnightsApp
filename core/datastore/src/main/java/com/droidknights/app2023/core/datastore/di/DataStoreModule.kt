package com.droidknights.app2023.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.droidknights.app2023.core.datastore.datasource.DefaultSessionPreferencesDataSource
import com.droidknights.app2023.core.datastore.datasource.DefaultSettingsPreferencesDataSource
import com.droidknights.app2023.core.datastore.api.datasource.SessionPreferencesDataSource
import com.droidknights.app2023.core.datastore.api.datasource.SettingsPreferencesDataSource
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
internal object DataStoreModule {
    private const val SETTING_DATASTORE_NAME = "SETTINGS_PREFERENCES"
    private const val SESSION_DATASTORE_NAME = "SESSION_PREFERENCES"
    private val Context.settingDataStore by preferencesDataStore(SETTING_DATASTORE_NAME)
    private val Context.sessionDataStore by preferencesDataStore(SESSION_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named("setting")
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.settingDataStore

    @Provides
    @Singleton
    @Named("session")
    fun provideSessionDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.sessionDataStore
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindModule {
    @Binds
    abstract fun bindSessionLocalDataSource(
        dataSource: DefaultSessionPreferencesDataSource,
    ): SessionPreferencesDataSource

    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultSettingsPreferencesDataSource,
    ): SettingsPreferencesDataSource
}
