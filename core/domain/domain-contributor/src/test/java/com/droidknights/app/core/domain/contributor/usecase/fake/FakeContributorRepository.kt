package com.droidknights.app.core.domain.contributor.usecase.fake

import com.droidknights.app.core.data.contributor.api.ContributorRepository
import com.droidknights.app.core.model.contributor.ContributorGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeContributorRepository(
    private val contributors: List<ContributorGroup>,
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<List<ContributorGroup>> =
        flowOf(contributors)
}
