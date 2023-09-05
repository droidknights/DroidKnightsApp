package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.SessionRepository
import com.droidknights.app2023.core.domain.usecase.api.BookmarkSessionUseCase
import javax.inject.Inject

internal class BookmarkSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : BookmarkSessionUseCase {

    override suspend operator fun invoke(sessionId: String, bookmark: Boolean) {
        return sessionRepository.bookmarkSession(sessionId, bookmark)
    }
}
