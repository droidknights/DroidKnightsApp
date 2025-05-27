package com.droidknights.app.core.data.session.di

import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.data.session.api.SessionApi
import com.droidknights.app.core.network.api.DroidknightsNetwork
import com.droidknights.app.core.network.api.create
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideSessionApi(
        droidknightsNetwork: DroidknightsNetwork,
        droidknightsBuildConfig: DroidknightsBuildConfig,
    ): SessionApi =
        droidknightsNetwork
            .create<SessionApi>(
                baseUrl = droidknightsBuildConfig.userDroidknightsUrl()
            )
}
