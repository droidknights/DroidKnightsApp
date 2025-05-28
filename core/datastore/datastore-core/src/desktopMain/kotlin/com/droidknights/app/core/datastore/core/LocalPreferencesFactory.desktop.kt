package com.droidknights.app.core.datastore.core

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath

actual class LocalPreferencesFactory actual constructor(private val fileName: String) {

    actual fun create(): LocalPreferences {
        val dataStore = PreferenceDataStoreFactory.createWithPath { fileName.toPath() }
        return DataStorePreferences(dataStore)
    }
}
