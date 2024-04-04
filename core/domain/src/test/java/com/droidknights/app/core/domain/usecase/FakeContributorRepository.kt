package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.ContributorRepository
import com.droidknights.app.core.model.Contributor

internal class FakeContributorRepository(
    private val contributors: List<Contributor>,
) : ContributorRepository {
    override suspend fun getContributors(owner: String, name: String): List<Contributor> {
        return contributors
    }
}
