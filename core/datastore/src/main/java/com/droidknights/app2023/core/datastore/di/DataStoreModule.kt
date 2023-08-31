package com.droidknights.app2023.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
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
