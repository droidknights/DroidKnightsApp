package com.droidknights.app.core.datastore.core

@Suppress("UnusedPrivateProperty")
actual class LocalPreferencesFactory actual constructor(private val fileName: String) {

    actual fun create(): LocalPreferences = InMemoryPreferences()
}
