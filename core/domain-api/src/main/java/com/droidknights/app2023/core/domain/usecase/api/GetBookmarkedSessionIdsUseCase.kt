package com.droidknights.app2023.core.domain.usecase.api

import kotlinx.coroutines.flow.Flow

interface GetBookmarkedSessionIdsUseCase {
    suspend operator fun invoke(): Flow<Set<String>>
}
