package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeContributorRepository(
    private val contributors: Map<Int, List<Contributor>>,
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<Map<Int, List<Contributor>>> =
        flowOf(contributors)
}
