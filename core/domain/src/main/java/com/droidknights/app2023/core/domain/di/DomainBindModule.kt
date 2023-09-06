package com.droidknights.app2023.core.domain.di

import com.droidknights.app2023.core.domain.usecase.api.BookmarkSessionUseCase
import com.droidknights.app2023.core.domain.usecase.BookmarkSessionUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionIdsUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionsUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetContributorsUseCase
import com.droidknights.app2023.core.domain.usecase.GetContributorsUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetSessionUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetSessionsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionsUseCaseImpl
import com.droidknights.app2023.core.domain.usecase.api.GetSponsorsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSponsorsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DomainBindModule {
    @Binds
    abstract fun bindBookmarkSessionUseCaseImpl(
        dataSource: BookmarkSessionUseCaseImpl,
    ): BookmarkSessionUseCase

    @Binds
    abstract fun bindGetBookmarkedSessionIdsUseCaseImpl(
        dataSource: GetBookmarkedSessionIdsUseCaseImpl,
    ): GetBookmarkedSessionIdsUseCase

    @Binds
    abstract fun bindGetBookmarkedSessionsUseCaseImpl(
        dataSource: GetBookmarkedSessionsUseCaseImpl,
    ): GetBookmarkedSessionsUseCase

    @Binds
    abstract fun bindGetContributorsUseCaseImpl(
        dataSource: GetContributorsUseCaseImpl,
    ): GetContributorsUseCase

    @Binds
    abstract fun bindGetSessionsUseCaseImpl(
        dataSource: GetSessionsUseCaseImpl,
    ): GetSessionsUseCase

    @Binds
    abstract fun bindGetSessionUseCaseImpl(
        dataSource: GetSessionUseCaseImpl,
    ): GetSessionUseCase

    @Binds
    abstract fun bindGetSponsorsUseCaseImpl(
        dataSource: GetSponsorsUseCaseImpl,
    ): GetSponsorsUseCase
}
