package com.droidknights.app2023.core.repo.contributor

import com.droidknights.app2023.core.repo.contributor.api.model.Contributor
import com.droidknights.app2023.core.repo.contributor.api.ContributorRepository
import com.droidknights.app2023.core.repo.contributor.mapper.toData
import com.droidknights.app2023.core.repo.contributor.network.GithubApi
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
