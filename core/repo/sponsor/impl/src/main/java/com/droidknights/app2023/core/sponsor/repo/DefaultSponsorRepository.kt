package com.droidknights.app2023.core.sponsor.repo

import com.droidknights.app2023.core.sponsor.repo.api.SponsorRepository
import com.droidknights.app2023.core.sponsor.repo.api.model.Sponsor
import com.droidknights.app2023.core.sponsor.repo.mapper.toData
import com.droidknights.app2023.core.sponsor.repo.network.SponsorApi
import javax.inject.Inject

internal class DefaultSponsorRepository @Inject constructor(
    private val githubRawApi: SponsorApi,
) : SponsorRepository {

    override suspend fun getSponsors(): List<Sponsor> {
        return githubRawApi.getSponsors().map { it.toData() }
    }
}
