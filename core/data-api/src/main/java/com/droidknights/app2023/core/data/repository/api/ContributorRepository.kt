package com.droidknights.app2023.core.data.repository.api

import com.droidknights.app2023.core.model.Contributor

interface ContributorRepository {

    suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor>
}
