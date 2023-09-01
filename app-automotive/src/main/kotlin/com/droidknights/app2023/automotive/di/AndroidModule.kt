package com.droidknights.app2023.automotive.di

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import com.droidknights.app2023.core.playback.session.SessionActivityIntentProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object AndroidModule {
    @Provides
    fun provideContext(app: Application): Context = app

    @Provides
    fun toPlayerIntentProvider(): SessionActivityIntentProvider =
        object : SessionActivityIntentProvider {
            override fun toPlayer(): PendingIntent? = null
        }
}