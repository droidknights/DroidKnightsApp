package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.model.ContributorEntity
import com.droidknights.app2023.core.data.repository.ContributorRepository

internal class FakeContributorRepository(
    private val contributors: List<ContributorEntity>,
) : ContributorRepository {
    override suspend fun getContributors(owner: String, name: String): List<ContributorEntity> {
        return contributors
    }
}
