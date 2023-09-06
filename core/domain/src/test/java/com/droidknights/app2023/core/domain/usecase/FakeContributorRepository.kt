package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.ContributorRepository
import com.droidknights.app2023.core.model.Contributor

internal class FakeContributorRepository(
    private val contributors: List<Contributor>,
) : ContributorRepository {
    override suspend fun getContributors(owner: String, name: String): List<Contributor> {
        return contributors
    }
}
