package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.model.SponsorEntity

interface SponsorRepository {
    
    suspend fun getSponsors(): List<SponsorEntity>
}
