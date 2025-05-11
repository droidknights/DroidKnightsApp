package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetBookmarkedSessionIdsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetBookmarkedSessionIdsUseCase {

    override operator fun invoke(): Flow<Set<String>> =
        sessionRepository.getBookmarkedSessionIds()
}
