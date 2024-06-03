package com.droidknights.app.core.data.repository

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.mapper.toData
import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorWithYears
import javax.inject.Inject

internal class DefaultContributorRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRawApi: GithubRawApi
) : ContributorRepository {

    override suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor> {
        return githubApi.getContributors(owner, name).map { it.toData() }
    }

    override suspend fun getContributorsWithYears(): List<ContributorWithYears> {
        return githubRawApi.getContributorWithYears().map { it.toData() }
    }
}
