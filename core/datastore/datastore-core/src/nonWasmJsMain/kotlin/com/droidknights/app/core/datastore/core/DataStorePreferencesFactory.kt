package com.droidknights.app.core.datastore.core

import androidx.datastore.preferences.core.PreferenceDataStoreFactory

class DataStorePreferencesFactory(
    private val pathProvider: PathProvider,
) {

    fun create(fileName: String): DataStorePreferences {
        val dataStore = PreferenceDataStoreFactory.createWithPath { pathProvider.getPath(fileName) }
        return DataStorePreferences(dataStore)
    }
}
