package com.droidknights.app.core.datastore.core.di

import com.droidknights.app.core.datastore.core.LocalPreferences
import com.droidknights.app.core.datastore.core.LocalPreferencesFactory
import org.koin.dsl.module

val coreDatastoreCoreModule = module {
    factory<LocalPreferences> { (fileName: String) -> LocalPreferencesFactory(fileName).create() }
}
