package com.droidknights.app.core.domain.contributor.di

import com.droidknights.app.core.domain.contributor.usecase.GetContributorsUseCaseImpl
import com.droidknights.app.core.domain.contributor.usecase.api.GetContributorsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ContributorDomainModule {

    @Binds
    abstract fun bindGetContributorsUseCase(
        getContributorsUseCase: GetContributorsUseCaseImpl,
    ): GetContributorsUseCase
}
