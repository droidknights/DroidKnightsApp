package com.droidknights.app.core.datastore.core

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

actual class LocalPreferencesFactory actual constructor(private val fileName: String) : KoinComponent {

    private val context: Context = get()

    actual fun create(): LocalPreferences {
        val producePath = context.filesDir.resolve(fileName).absolutePath
        val dataStore = PreferenceDataStoreFactory.createWithPath { producePath.toPath() }
        return DataStorePreferences(dataStore)
    }
}
