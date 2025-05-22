package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionIdsUseCase
import kotlinx.coroutines.flow.Flow

internal class GetBookmarkedSessionIdsUseCaseImpl(
    private val sessionRepository: SessionRepository,
) : GetBookmarkedSessionIdsUseCase {

    override operator fun invoke(): Flow<Set<String>> =
        sessionRepository.getBookmarkedSessionIds()
}
