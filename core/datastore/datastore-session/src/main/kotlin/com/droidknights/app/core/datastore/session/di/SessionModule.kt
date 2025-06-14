package com.droidknights.app.core.datastore.session.di

import com.droidknights.app.core.datastore.session.DefaultSessionPreferencesDataSource
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SessionModule {

    @Binds
    abstract fun bindSessionPreferencesDataSource(
        dataSource: DefaultSessionPreferencesDataSource,
    ): SessionPreferencesDataSource
}
