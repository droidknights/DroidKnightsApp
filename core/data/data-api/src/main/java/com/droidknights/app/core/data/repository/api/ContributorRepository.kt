package com.droidknights.app.core.data.repository.api

import com.droidknights.app.core.model.ContributorGroup
import kotlinx.coroutines.flow.Flow

interface ContributorRepository {

    fun flowContributors(
        owner: String,
        name: String,
    ): Flow<List<ContributorGroup>>
}
