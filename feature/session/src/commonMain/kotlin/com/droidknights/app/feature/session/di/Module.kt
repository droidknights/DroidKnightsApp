package com.droidknights.app.feature.session.di

import com.droidknights.app.feature.session.SessionDetailViewModel
import com.droidknights.app.feature.session.SessionViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSessionModule = module {
    viewModelOf(::SessionDetailViewModel)
    viewModelOf(::SessionViewModel)
}
