package com.droidknights.app.feature.license.di

import com.droidknights.app.feature.license.LicenseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureLicensesModule = module {
    viewModelOf(::LicenseViewModel)
}
