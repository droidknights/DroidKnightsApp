package com.droidknights.app.core.datastore.datasource.di

import com.droidknights.app.core.datastore.datasource.DefaultSessionPreferencesDataSource
import com.droidknights.app.core.datastore.datasource.SessionPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DatastoreModule {

    @Binds
    abstract fun bindSessionLocalDataSource(
        dataSource: DefaultSessionPreferencesDataSource,
    ): SessionPreferencesDataSource
}
