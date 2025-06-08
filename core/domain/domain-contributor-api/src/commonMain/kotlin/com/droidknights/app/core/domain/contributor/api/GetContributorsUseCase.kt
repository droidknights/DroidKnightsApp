package com.droidknights.app.core.domain.contributor.api

import com.droidknights.app.core.model.contributor.ContributorGroup
import kotlinx.coroutines.flow.Flow

interface GetContributorsUseCase {

    operator fun invoke(): Flow<List<ContributorGroup>>
}
