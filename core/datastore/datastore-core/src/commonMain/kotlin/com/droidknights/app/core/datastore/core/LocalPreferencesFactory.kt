package com.droidknights.app.core.datastore.core

expect class LocalPreferencesFactory(fileName: String) {

    fun create(): LocalPreferences
}
