package com.droidknights.app.core.data.repository.api

import com.droidknights.app.core.model.Sponsor

interface SponsorRepository {

    suspend fun getSponsors(): List<Sponsor>
}
