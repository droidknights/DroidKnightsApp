package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.ContributorGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeContributorRepository(
    private val contributors: List<ContributorGroup>,
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<List<ContributorGroup>> =
        flowOf(contributors)
}
