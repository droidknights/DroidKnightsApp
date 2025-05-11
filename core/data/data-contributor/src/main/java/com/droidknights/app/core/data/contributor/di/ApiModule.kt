package com.droidknights.app.core.data.contributor.di

import android.content.Context
import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.data.contributor.api.DroidnightsContributorsApi
import com.droidknights.app.core.data.contributor.api.GithubContributorsApi
import com.droidknights.app.core.data.contributor.api.fake.AssetsDroidnightsContributorsApi
import com.droidknights.app.core.network.api.DroidknightsNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideGithubApi(
        droidknightsNetwork: DroidknightsNetwork,
        droidknightsBuildConfig: DroidknightsBuildConfig,
    ): GithubContributorsApi =
        droidknightsNetwork
            .create(
                baseUrl = droidknightsBuildConfig.gitHubUrl(),
                service = GithubContributorsApi::class.java,
            )

    @Provides
    @Singleton
    fun provideGitRawApi(
        droidknightsNetwork: DroidknightsNetwork,
        droidknightsBuildConfig: DroidknightsBuildConfig,
    ): DroidnightsContributorsApi =
        droidknightsNetwork
            .create(
                baseUrl = droidknightsBuildConfig.userDroidknightsUrl(),
                service = DroidnightsContributorsApi::class.java,
            )

    @Provides
    @Singleton
    fun provideGithubRawApi(
        @ApplicationContext context: Context,
        json: Json,
    ): AssetsDroidnightsContributorsApi =
        AssetsDroidnightsContributorsApi(
            json = json,
            contributors = context.assets.open("contributors.json"),
        )
}
