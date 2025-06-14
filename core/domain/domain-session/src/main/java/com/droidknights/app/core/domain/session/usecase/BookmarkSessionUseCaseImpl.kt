package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.usecase.api.BookmarkSessionUseCase
import javax.inject.Inject

internal class BookmarkSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : BookmarkSessionUseCase {

    override suspend operator fun invoke(sessionId: String, bookmark: Boolean) =
        sessionRepository.bookmarkSession(sessionId, bookmark)
}
