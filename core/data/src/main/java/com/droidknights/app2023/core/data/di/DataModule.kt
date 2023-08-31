package com.droidknights.app2023.core.data.di

import android.content.Context
import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.api.fake.AssetsGithubRawApi
import com.droidknights.app2023.core.data.repository.ContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultSessionRepository
import com.droidknights.app2023.core.data.repository.DefaultSettingsRepository
import com.droidknights.app2023.core.data.repository.DefaultSponsorRepository
import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.data.repository.SettingsRepository
import com.droidknights.app2023.core.data.repository.SponsorRepository
import com.droidknights.app2023.core.datastore.datasource.DefaultSessionPreferencesDataSource
import com.droidknights.app2023.core.datastore.datasource.SessionPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsContributorRepository(
        repository: DefaultContributorRepository,
    ): ContributorRepository

    @Binds
    abstract fun bindsSettingsRepository(
        repository: DefaultSettingsRepository,
    ): SettingsRepository

    @Binds
    abstract fun bindSessionLocalDataSource(
        dataSource: DefaultSessionPreferencesDataSource,
    ): SessionPreferencesDataSource

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {

        @Provides
        @Singleton
        fun provideSponsorRepository(
            githubRawApi: GithubRawApi,
        ): SponsorRepository = DefaultSponsorRepository(githubRawApi)

        @Provides
        @Singleton
        fun provideSessionRepository(
            githubRawApi: GithubRawApi,
            sessionDataSource: SessionPreferencesDataSource,
        ): SessionRepository = DefaultSessionRepository(githubRawApi, sessionDataSource)

        @Provides
        @Singleton
        fun provideGithubRawApi(
            @ApplicationContext context: Context,
        ): AssetsGithubRawApi = AssetsGithubRawApi(context)
    }
}
