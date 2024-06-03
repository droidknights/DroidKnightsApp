package com.droidknights.app.core.data.repository.api

import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorWithYears

interface ContributorRepository {

    suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor>

    suspend fun getContributorsWithYears(): List<ContributorWithYears>
}
