package com.droidknights.app2023.feature.session.di

import com.droidknights.app2023.feature.session.api.SessionDetailNavController
import com.droidknights.app2023.feature.session.api.SessionNavController
import com.droidknights.app2023.feature.session.api.SessionNavGraph
import com.droidknights.app2023.feature.session.navigation.SessionDetailNavControllerImpl
import com.droidknights.app2023.feature.session.navigation.SessionNavControllerImpl
import com.droidknights.app2023.feature.session.navigation.SessionNavGraphImpl
import com.droidknights.app2023.feature.session.usecase.BookmarkSessionUseCase
import com.droidknights.app2023.feature.session.usecase.BookmarkSessionUseCaseImpl
import com.droidknights.app2023.feature.session.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.feature.session.usecase.GetBookmarkedSessionIdsUseCaseImpl
import com.droidknights.app2023.feature.session.usecase.GetSessionUseCase
import com.droidknights.app2023.feature.session.usecase.GetSessionUseCaseImpl
import com.droidknights.app2023.feature.session.usecase.GetSessionsUseCase
import com.droidknights.app2023.feature.session.usecase.GetSessionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SessionBindModule {
    @Binds
    abstract fun sessionNavControllerImpl(
        dataSource: SessionNavControllerImpl,
    ): SessionNavController

    @Binds
    abstract fun sessionDetailNavControllerImpl(
        dataSource: SessionDetailNavControllerImpl,
    ): SessionDetailNavController

    @Binds
    abstract fun sessionNavGraphImpl(
        dataSource: SessionNavGraphImpl,
    ): SessionNavGraph

}


@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UsecaseBindModule {
    @Binds
    @ViewModelScoped
    abstract fun provideGetSessionsUseCase(
        dataSource: GetSessionsUseCaseImpl,
    ): GetSessionsUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetSessionUseCase(
        dataSource: GetSessionUseCaseImpl,
    ): GetSessionUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetBookmarkedSessionIdsUseCase(
        dataSource: GetBookmarkedSessionIdsUseCaseImpl,
    ): GetBookmarkedSessionIdsUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideBookmarkSessionUseCase(
        dataSource: BookmarkSessionUseCaseImpl,
    ): BookmarkSessionUseCase
}
