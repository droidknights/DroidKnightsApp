package com.droidknights.app.core.data.session.di

import com.droidknights.app.core.data.session.SessionRepositoryImpl
import com.droidknights.app.core.data.session.api.SessionRepository
import org.koin.dsl.module

val coreDataSessionModule = module {
    single<SessionRepository> { SessionRepositoryImpl() }
}
