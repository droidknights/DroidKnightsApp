package com.droidknights.app.core.domain.session.usecase.api

interface BookmarkSessionUseCase {

    suspend operator fun invoke(sessionId: String, bookmark: Boolean)
}
