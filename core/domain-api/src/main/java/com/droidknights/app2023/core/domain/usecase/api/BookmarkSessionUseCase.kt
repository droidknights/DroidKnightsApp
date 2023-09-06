package com.droidknights.app2023.core.domain.usecase.api

interface BookmarkSessionUseCase {
    suspend operator fun invoke(sessionId: String, bookmark: Boolean)
}
