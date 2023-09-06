package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.mapper.toData
import com.droidknights.app2023.core.data.repository.api.SponsorRepository
import com.droidknights.app2023.core.model.Sponsor
import javax.inject.Inject

internal class DefaultSponsorRepository @Inject constructor(
    private val githubRawApi: GithubRawApi,
) : SponsorRepository {

    override suspend fun getSponsors(): List<Sponsor> {
        return githubRawApi.getSponsors().map { it.toData() }
    }
}
