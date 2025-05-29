package com.droidknights.app.core.datastore.settings.di

import com.droidknights.app.core.datastore.core.LocalPreferences
import com.droidknights.app.core.datastore.settings.DefaultSettingsPreferencesDataSource
import com.droidknights.app.core.datastore.settings.api.SettingsPreferenceDataSource
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val coreDatastoreSettingsModule = module {
    single<SettingsPreferenceDataSource> {
        DefaultSettingsPreferencesDataSource(get<LocalPreferences> { parametersOf(SETTINGS_PREFERENCES_NAME) })
    }
}

private const val SETTINGS_PREFERENCES_NAME = "SETTINGS_PREFERENCES.preferences_pb"
