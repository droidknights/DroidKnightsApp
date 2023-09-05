package com.droidknights.app2023.core.data.repository.api

import com.droidknights.app2023.core.model.Sponsor

interface SponsorRepository {

    suspend fun getSponsors(): List<Sponsor>
}
