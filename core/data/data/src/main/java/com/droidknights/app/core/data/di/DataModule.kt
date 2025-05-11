package com.droidknights.app.core.data.di

import android.content.Context
import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.fake.AssetsGithubRawApi
import com.droidknights.app.core.data.repository.DefaultContributorRepository
import com.droidknights.app.core.data.repository.api.ContributorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {

        @Provides
        @Singleton
        fun provideContributorRepository(
            githubApi: GithubApi,
            githubRawApi: AssetsGithubRawApi,
        ): ContributorRepository =
            DefaultContributorRepository(githubApi, githubRawApi)

        @Provides
        @Singleton
        fun provideGithubRawApi(
            @ApplicationContext context: Context,
            json: Json,
        ): AssetsGithubRawApi =
            AssetsGithubRawApi(
                json = json,
                contributors = context.assets.open("contributors.json"),
            )
    }
}
