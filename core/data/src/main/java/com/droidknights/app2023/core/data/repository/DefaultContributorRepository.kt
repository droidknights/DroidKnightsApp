package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.GithubApi
import com.droidknights.app2023.core.data.mapper.toData
import com.droidknights.app2023.core.model.Contributor
import javax.inject.Inject

internal class DefaultContributorRepository @Inject constructor(
    private val githubApi: GithubApi,
) : ContributorRepository {

    override suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor> {
        return githubApi.getContributors(owner, name)
            .map { it.toData() }
    }
}
