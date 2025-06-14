package com.droidknights.app.core.network.di

import android.util.Log
import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.network.DroidknightsNetworkImpl
import com.droidknights.app.core.network.api.DroidknightsNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(
        interceptors: Provider<Set<@JvmSuppressWildcards Interceptor>>,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                interceptors.get().forEach { addInterceptor(it) }
            }
            .build()

    @Provides
    @Singleton
    @IntoSet
    fun providerHttpLoggingInterceptor(
        droidknightsBuildConfig: DroidknightsBuildConfig,
    ): Interceptor =
        HttpLoggingInterceptor {
            if (droidknightsBuildConfig.isDebug()) {
                Log.d("Droidknights", it)
            }
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

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
