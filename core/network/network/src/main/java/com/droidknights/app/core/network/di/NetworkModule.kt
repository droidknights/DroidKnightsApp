package com.droidknights.app.core.network.di

import com.droidknights.app.core.network.DroidknightsNetworkImpl
import com.droidknights.app.core.network.api.DroidknightsNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

    @Provides
    fun provideDroidknightsNetwork(
        retrofitBuilder: Retrofit.Builder,
    ): DroidknightsNetwork =
        DroidknightsNetworkImpl(retrofitBuilder)

    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
}
