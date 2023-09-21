package com.droidknights.app2023.feature.session.usecase

import com.droidknights.app2023.core.repo.session.api.SessionRepository
import javax.inject.Inject

internal interface BookmarkSessionUseCase {
    suspend operator fun invoke(sessionId: String, bookmark: Boolean)
}

internal class BookmarkSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : BookmarkSessionUseCase {

    override suspend operator fun invoke(sessionId: String, bookmark: Boolean) {
        return sessionRepository.bookmarkSession(sessionId, bookmark)
    }
}
