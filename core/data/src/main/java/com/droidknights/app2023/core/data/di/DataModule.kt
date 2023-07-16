package com.droidknights.app2023.core.data.di

import com.droidknights.app2023.core.data.repository.ContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultSponsorRepository
import com.droidknights.app2023.core.data.repository.SponsorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    
    @Binds
    abstract fun bindsContributorRepository(
        repository: DefaultContributorRepository,
    ): ContributorRepository
    
    @Binds
    abstract fun bindsSponsorRepository(
        repository: DefaultSponsorRepository,
    ): SponsorRepository
}
