package com.droidknights.app2023.core.domain.usecase.api

import com.droidknights.app2023.core.model.Session
import kotlinx.coroutines.flow.Flow

interface GetBookmarkedSessionsUseCase {
    suspend operator fun invoke(): Flow<List<Session>>
}
