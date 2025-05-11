package com.droidknights.app.widget.di

import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetModule {

    fun getBookmarkedSessionsUseCase(): GetBookmarkedSessionsUseCase

    fun getSessionUseCase(): GetSessionUseCase
}
