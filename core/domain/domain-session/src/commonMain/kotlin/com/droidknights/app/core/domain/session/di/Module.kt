package com.droidknights.app.core.domain.session.di

import com.droidknights.app.core.domain.session.api.usecase.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.api.usecase.DeleteBookmarkedSessionUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetSessionUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetSessionsUseCase
import com.droidknights.app.core.domain.session.usecase.BookmarkSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.DeleteBookmarkedSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetBookmarkedSessionIdsUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetBookmarkedSessionsUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetSessionUseCaseImpl
import com.droidknights.app.core.domain.session.usecase.GetSessionsUseCaseImpl
import org.koin.dsl.module

val coreDomainSessionModule = module {
    factory<BookmarkSessionUseCase> { BookmarkSessionUseCaseImpl(get()) }
    factory<DeleteBookmarkedSessionUseCase> { DeleteBookmarkedSessionUseCaseImpl(get()) }
    factory<GetBookmarkedSessionIdsUseCase> { GetBookmarkedSessionIdsUseCaseImpl(get()) }
    factory<GetBookmarkedSessionsUseCase> { GetBookmarkedSessionsUseCaseImpl(get(), get()) }
    factory<GetSessionsUseCase> { GetSessionsUseCaseImpl(get()) }
    factory<GetSessionUseCase> { GetSessionUseCaseImpl(get()) }
}
