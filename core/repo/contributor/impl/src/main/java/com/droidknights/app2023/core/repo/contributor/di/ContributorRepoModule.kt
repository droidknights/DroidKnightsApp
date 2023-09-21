package com.droidknights.app2023.core.repo.contributor.di

import com.droidknights.app2023.core.repo.contributor.api.ContributorRepository
import com.droidknights.app2023.core.repo.contributor.DefaultContributorRepository
import com.droidknights.app2023.core.repo.contributor.network.GithubApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ContributorRepoModule {

    @Provides
    @Singleton
    fun provideGithubApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): GithubApi {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(GithubApi::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ContributorRepoBindModule {
    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultContributorRepository,
    ): ContributorRepository
}

