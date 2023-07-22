package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.model.Sponsor

interface SponsorRepository {

    suspend fun getSponsors(): List<Sponsor>
}
