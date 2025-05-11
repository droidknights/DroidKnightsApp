package com.droidknights.app.core.data.di

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.network.api.DroidknightsNetwork
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
    fun provideGithubApi(
        droidknightsNetwork: DroidknightsNetwork,
    ): GithubApi =
        droidknightsNetwork
            .create(
                baseUrl = "https://api.github.com/",
                service = GithubApi::class.java,
            )

    @Provides
    @Singleton
    fun provideGitRawApi(
        droidknightsNetwork: DroidknightsNetwork,
    ): GithubRawApi =
        droidknightsNetwork
            .create(
                baseUrl = "https://raw.githubusercontent.com/",
                service = GithubRawApi::class.java,
            )
}
