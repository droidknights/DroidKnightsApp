package com.droidknights.app.core.data.sponsor.api

import com.droidknights.app.core.model.sponsor.Sponsor

interface SponsorRepository {

    suspend fun getSponsors(): List<Sponsor>
}
