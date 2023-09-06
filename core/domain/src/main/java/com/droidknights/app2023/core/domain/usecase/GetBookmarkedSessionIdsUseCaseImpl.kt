package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.SessionRepository
import com.droidknights.app2023.core.domain.usecase.api.GetBookmarkedSessionIdsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetBookmarkedSessionIdsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetBookmarkedSessionIdsUseCase {

    override suspend operator fun invoke(): Flow<Set<String>> {
        return sessionRepository.getBookmarkedSessionIds()
    }
}
