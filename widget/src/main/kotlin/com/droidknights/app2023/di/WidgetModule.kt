package com.droidknights.app2023.di

import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetModule {
    fun getBookmarkedSessionsUseCase(): GetBookmarkedSessionsUseCase

    fun getSessionUseCase(): GetSessionUseCase
}