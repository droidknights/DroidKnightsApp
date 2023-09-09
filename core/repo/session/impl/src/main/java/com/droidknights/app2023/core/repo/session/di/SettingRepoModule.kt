package com.droidknights.app2023.core.repo.session.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.droidknights.app2023.core.repo.session.DefaultSessionRepository
import com.droidknights.app2023.core.repo.session.SessionPreferencesDataSource
import com.droidknights.app2023.core.repo.session.api.SessionRepository
import com.droidknights.app2023.core.repo.session.network.SessionApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SessionRepoModule {
    private const val SESSION_DATASTORE_NAME = "SESSION_PREFERENCES"
    private val Context.settingDataStore by preferencesDataStore(SESSION_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named(SessionPreferencesDataSource.PreferencesKey.DATA_STORE)
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.settingDataStore

    @Provides
    @Singleton
    fun provideSponsorApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): SessionApi {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(SessionApi::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SessionRepoBindModule {
    @Binds
    abstract fun bindSettingsPreferencesDataSource(
        dataSource: DefaultSessionRepository,
    ): SessionRepository
}
