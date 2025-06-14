package com.droidknights.app.core.data.contributor.api

import com.droidknights.app.core.model.contributor.ContributorGroup
import kotlinx.coroutines.flow.Flow

interface ContributorRepository {

    fun flowContributors(
        owner: String,
        name: String,
    ): Flow<List<ContributorGroup>>
}
