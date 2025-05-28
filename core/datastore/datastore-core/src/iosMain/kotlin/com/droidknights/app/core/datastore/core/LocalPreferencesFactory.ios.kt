package com.droidknights.app.core.datastore.core

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class LocalPreferencesFactory actual constructor(private val fileName: String) {

    @OptIn(ExperimentalForeignApi::class)
    actual fun create(): LocalPreferences {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val producePath = requireNotNull(documentDirectory).path + "/$fileName"
        val dataStore = PreferenceDataStoreFactory.createWithPath { producePath.toPath() }
        return DataStorePreferences(dataStore)
    }
}
