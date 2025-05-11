package com.droidknights.app.core.domain.session.di

import com.droidknights.app.core.domain.session.usecase.BookmarkSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.DeleteBookmarkedSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetBookmarkedSessionIdsUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetBookmarkedSessionsUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetSessionsUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.api.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.DeleteBookmarkedSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SessionDomainModule {

    @Binds
    abstract fun bindBookmarkSessionUseCase(
        bookmarkSessionUseCase: BookmarkSessionUseCaseImpl,
    ): BookmarkSessionUseCase

    @Binds
    abstract fun bindDeleteBookmarkedSessionUseCase(
        deleteBookmarkedSessionUseCase: DeleteBookmarkedSessionUseCaseImpl,
    ): DeleteBookmarkedSessionUseCase

    @Binds
    abstract fun bindGetBookmarkedSessionIdsUseCase(
        getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCaseImpl,
    ): GetBookmarkedSessionIdsUseCase

    @Binds
    abstract fun bindGetBookmarkedSessionsUseCase(
        getBookmarkedSessionsUseCase: GetBookmarkedSessionsUseCaseImpl,
    ): GetBookmarkedSessionsUseCase

    @Binds
    abstract fun bindGetSessionsUseCase(
        getSessionsUseCase: GetSessionsUseCaseImpl,
    ): GetSessionsUseCase

    @Binds
    abstract fun bindGetSessionUseCase(
        getSessionUseCase: GetSessionUseCaseImpl,
    ): GetSessionUseCase
}
