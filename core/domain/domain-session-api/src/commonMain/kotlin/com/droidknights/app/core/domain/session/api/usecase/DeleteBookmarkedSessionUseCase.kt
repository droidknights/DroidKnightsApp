package com.droidknights.app.core.domain.session.api.usecase

interface DeleteBookmarkedSessionUseCase {

    suspend operator fun invoke(sessionIds: Set<String>)
}
