package com.droidknights.app.core.domain.sponsor.di

import com.droidknights.app.core.domain.sponsor.usecase.GetSponsorsUseCaseImpl
import com.droidknights.app.core.domain.sponsor.usecase.api.GetSponsorsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SponsorDomainModule {

    @Binds
    abstract fun bindGetSponsorsUseCase(
        getSponsorsUseCase: GetSponsorsUseCaseImpl,
    ): GetSponsorsUseCase
}
