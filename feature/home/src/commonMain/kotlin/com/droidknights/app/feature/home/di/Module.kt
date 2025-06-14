package com.droidknights.app.feature.home.di

import com.droidknights.app.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {
    viewModelOf(::HomeViewModel)
}
