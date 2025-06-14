package com.droidknights.app.core.data.contributor.di

import com.droidknights.app.core.data.contributor.ContributorRepositoryImpl
import com.droidknights.app.core.data.contributor.api.ContributorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ContributorModule {

    @Binds
    @Singleton
    abstract fun provideContributorRepository(
        contributorRepository: ContributorRepositoryImpl,
    ): ContributorRepository
}
