package com.droidknights.app.core.data.sponsor

import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.data.sponsor.api.SponsorApi
import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.data.sponsor.mapper.toData
import com.droidknights.app.core.model.sponsor.Sponsor
import javax.inject.Inject

internal class SponsorRepositoryImpl @Inject constructor(
    private val sponsorApi: SponsorApi,
    private val droidknightsBuildConfig: DroidknightsBuildConfig,
) : SponsorRepository {

    override suspend fun getSponsors(): List<Sponsor> {
        return sponsorApi.getSponsors(
            url = droidknightsBuildConfig.sponsorDataUrl(),
        ).map { it.toData() }
    }
}
