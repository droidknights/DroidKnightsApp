package com.droidknights.app.core.datastore.core.di

import com.droidknights.app.core.datastore.core.InMemoryPreferences
import com.droidknights.app.core.datastore.core.LocalPreferences
import org.koin.dsl.module

private val coreDatastoreCoreModule = module {
    factory<LocalPreferences> { InMemoryPreferences() }
}

actual val coreDatastoreCoreModules = listOf(coreDatastoreCoreModule)
