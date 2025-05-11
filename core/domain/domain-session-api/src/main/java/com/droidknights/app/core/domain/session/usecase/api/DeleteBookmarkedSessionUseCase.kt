package com.droidknights.app.core.domain.session.usecase.api

interface DeleteBookmarkedSessionUseCase {

    suspend operator fun invoke(sessionIds: Set<String>)
}
