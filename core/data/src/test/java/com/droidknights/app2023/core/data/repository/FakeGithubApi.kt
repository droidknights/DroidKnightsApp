package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.ContributorResponse
import com.droidknights.app2023.core.data.api.GithubApi

internal class FakeGithubApi(private val contributors: List<ContributorResponse>) : GithubApi {
    override suspend fun getContributors(owner: String, name: String): List<ContributorResponse> {
        return contributors
    }
}
