package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.SessionRepository
import javax.inject.Inject

class BookmarkSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String, bookmark: Boolean) =
        sessionRepository.bookmarkSession(sessionId, bookmark)
}
