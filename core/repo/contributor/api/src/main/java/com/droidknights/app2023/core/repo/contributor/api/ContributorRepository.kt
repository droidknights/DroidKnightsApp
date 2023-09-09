package com.droidknights.app2023.core.repo.contributor.api

import com.droidknights.app2023.core.repo.contributor.api.model.Contributor


interface ContributorRepository {

    suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor>
}
