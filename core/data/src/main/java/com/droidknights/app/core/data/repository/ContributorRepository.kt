package com.droidknights.app.core.data.repository

import com.droidknights.app.core.model.Contributor

interface ContributorRepository {

    suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor>
}
