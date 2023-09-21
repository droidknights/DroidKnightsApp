package com.droidknights.app2023.feature.session.usecase

import com.droidknights.app2023.core.repo.session.api.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal interface GetBookmarkedSessionIdsUseCase {

    suspend operator fun invoke(): Flow<Set<String>>
}

internal class GetBookmarkedSessionIdsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetBookmarkedSessionIdsUseCase {

    override suspend operator fun invoke(): Flow<Set<String>> {
        return sessionRepository.getBookmarkedSessionIds()
    }
}
