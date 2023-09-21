package com.droidknights.app2023.core.sponsor.repo.di

import com.droidknights.app2023.core.sponsor.repo.DefaultSponsorRepository
import com.droidknights.app2023.core.sponsor.repo.api.SponsorRepository
import com.droidknights.app2023.core.sponsor.repo.network.SponsorApi
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
internal object SponsorRepoModule {

    @Provides
    @Singleton
    fun provideSponsorApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): SponsorApi {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(SponsorApi::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SponsorRepoBindModule {
    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultSponsorRepository,
    ): SponsorRepository
}

