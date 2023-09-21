package com.droidknights.app2023.feature.contributor.di

import com.droidknights.app2023.feature.contributor.api.ContributorNavController
import com.droidknights.app2023.feature.contributor.api.ContributorNavGraph
import com.droidknights.app2023.feature.contributor.navigation.ContributorNavControllerImpl
import com.droidknights.app2023.feature.contributor.navigation.ContributorNavGraphImpl
import com.droidknights.app2023.feature.contributor.usecase.GetContributorsUseCase
import com.droidknights.app2023.feature.contributor.usecase.GetContributorsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class ContributorBindModule {
    @Binds
    abstract fun contributorNavControllerImpl(
        dataSource: ContributorNavControllerImpl,
    ): ContributorNavController

    @Binds
    abstract fun contributorNavGraphImpl(
        dataSource: ContributorNavGraphImpl,
    ): ContributorNavGraph

}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UsecaseBindModule {
    @Binds
    @ViewModelScoped
    abstract fun getContributorsUseCase(
        dataSource: GetContributorsUseCaseImpl,
    ): GetContributorsUseCase
}
