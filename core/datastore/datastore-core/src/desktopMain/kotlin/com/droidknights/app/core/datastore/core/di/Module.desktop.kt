package com.droidknights.app.core.datastore.core.di

import com.droidknights.app.core.datastore.core.PathProvider
import org.koin.dsl.module

internal actual val pathProviderModule = module {
    single { PathProvider() }
}
