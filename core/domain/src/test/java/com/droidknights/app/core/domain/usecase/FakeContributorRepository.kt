package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorWithYears

internal class FakeContributorRepository(
    private val contributors: List<Contributor>,
    private val contributorWithYears: List<ContributorWithYears>
) : ContributorRepository {

    override suspend fun getContributors(owner: String, name: String): List<Contributor> {
        return contributors
    }

    override suspend fun getContributorsWithYears(): List<ContributorWithYears> {
        return contributorWithYears
    }
}
