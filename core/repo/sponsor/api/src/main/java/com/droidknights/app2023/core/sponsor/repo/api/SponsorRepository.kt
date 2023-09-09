package com.droidknights.app2023.core.sponsor.repo.api

import com.droidknights.app2023.core.sponsor.repo.api.model.Sponsor

interface SponsorRepository {

    suspend fun getSponsors(): List<Sponsor>
}
