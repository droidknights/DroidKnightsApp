package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.model.ContributorEntity

interface ContributorRepository {

    suspend fun getContributors(
        owner: String,
        name: String,
    ): List<ContributorEntity>
}
