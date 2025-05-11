package com.droidknights.app.core.domain.session.usecase.api

import kotlinx.coroutines.flow.Flow

interface GetBookmarkedSessionIdsUseCase {

    operator fun invoke(): Flow<Set<String>>
}
