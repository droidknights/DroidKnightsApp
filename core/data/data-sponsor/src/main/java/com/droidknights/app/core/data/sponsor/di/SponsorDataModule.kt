package com.droidknights.app.core.data.sponsor.di

import com.droidknights.app.core.data.sponsor.SponsorRepositoryImpl
import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SponsorDataModule {

    @Binds
    @Singleton
    abstract fun bindSponsorRepository(
        sponsorRepository: SponsorRepositoryImpl,
    ): SponsorRepository
}
