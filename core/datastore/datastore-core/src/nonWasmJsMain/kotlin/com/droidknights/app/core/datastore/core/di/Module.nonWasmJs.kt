package com.droidknights.app.core.datastore.core.di

import com.droidknights.app.core.datastore.core.DataStorePreferencesFactory
import com.droidknights.app.core.datastore.core.LocalPreferences
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val pathProviderModule: Module

private val coreDatastoreCoreModule = module {
    single { DataStorePreferencesFactory(get()) }
    factory<LocalPreferences> { (fileName: String) ->
        get<DataStorePreferencesFactory>().create(fileName)
    }
}

actual val coreDatastoreCoreModules: List<Module> = listOf(
    coreDatastoreCoreModule,
    pathProviderModule,
)
