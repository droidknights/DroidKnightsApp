package com.droidknights.app.core.data.repository

import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.mapper.toData
import com.droidknights.app.core.data.repository.api.SponsorRepository
import com.droidknights.app.core.model.Sponsor
import javax.inject.Inject
import javax.inject.Named

internal class DefaultSponsorRepository @Inject constructor(
    @Named("GithubRawApi") private val githubRawApi: GithubRawApi,
) : SponsorRepository {

    override suspend fun getSponsors(): List<Sponsor> {
        return githubRawApi.getSponsors().map { it.toData() }
    }
}
