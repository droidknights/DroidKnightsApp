package com.droidknights.app.core.domain.session.api.usecase

interface BookmarkSessionUseCase {

    suspend operator fun invoke(sessionId: String, bookmark: Boolean)
}
