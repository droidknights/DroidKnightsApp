package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import javax.inject.Inject

class BookmarkSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String, bookmark: Boolean) {
        return sessionRepository.bookmarkSession(sessionId, bookmark)
    }
}
