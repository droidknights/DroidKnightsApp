package com.droidknights.app.core.router.di

import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.router.internal.navigator.InternalNavigator
import com.droidknights.app.core.router.internal.navigator.NavigatorImpl
import com.droidknights.app.core.router.internal.viewmodel.RouterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

val coreRouterModule = module {
    viewModelOf(::RouterViewModel)
    single { NavigatorImpl() } binds arrayOf(InternalNavigator::class, Navigator::class)
}
