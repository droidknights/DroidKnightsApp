package com.droidknights.app.core.datastore.session.di

import com.droidknights.app.core.datastore.core.LocalPreferences
import com.droidknights.app.core.datastore.session.DefaultSessionPreferencesDataSource
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val coreDatastoreSessionModule = module {
    single<SessionPreferencesDataSource> {
        DefaultSessionPreferencesDataSource(get<LocalPreferences> { parametersOf(SESSION_PREFERENCES_NAME) })
    }
}

private const val SESSION_PREFERENCES_NAME = "SESSION_PREFERENCES.preferences_pb"
