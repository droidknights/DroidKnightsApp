package com.droidknights.app.core.domain.session.api.usecase

import kotlinx.coroutines.flow.Flow

interface GetBookmarkedSessionIdsUseCase {

    operator fun invoke(): Flow<Set<String>>
}
