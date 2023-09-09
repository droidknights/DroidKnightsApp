package com.droidknights.app2023.feature.home.di

import com.droidknights.app2023.feature.home.api.HomeNavController
import com.droidknights.app2023.feature.home.api.HomeNavGraph
import com.droidknights.app2023.feature.home.navigation.HomeNavControllerImpl
import com.droidknights.app2023.feature.home.navigation.HomeNavGraphImpl
import com.droidknights.app2023.feature.home.navigation.HomeTab
import com.droidknights.app2023.feature.home.usecase.GetSponsorsUseCase
import com.droidknights.app2023.feature.home.usecase.GetSponsorsUseCaseImpl
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
internal abstract class HomeBindModule {
    @Binds
    abstract fun homeNavControllerImpl(
        dataSource: HomeNavControllerImpl,
    ): HomeNavController

    @Binds
    abstract fun homeNavGraphImpl(
        dataSource: HomeNavGraphImpl,
    ): HomeNavGraph

    @Binds
    @IntoSet
    abstract fun homeTab(
        homeTab: HomeTab,
    ): DroidKnightsTab

}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UsecaseBindModule {
    @Binds
    @ViewModelScoped
    abstract fun getSponsorsUseCase(
        dataSource: GetSponsorsUseCaseImpl,
    ): GetSponsorsUseCase
}
