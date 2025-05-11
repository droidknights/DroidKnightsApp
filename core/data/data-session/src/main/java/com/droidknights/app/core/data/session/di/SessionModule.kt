package com.droidknights.app.core.data.session.di

import com.droidknights.app.core.data.session.SessionRepositoryImpl
import com.droidknights.app.core.data.session.api.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SessionModule {

    @Binds
    @Singleton
    abstract fun provideSessionRepository(
        sessionRepository: SessionRepositoryImpl,
    ): SessionRepository
}
