package com.droidknights.app.core.domain.sponsor.api

import com.droidknights.app.core.model.Sponsor

interface GetSponsorsUseCase {

    suspend operator fun invoke(): List<Sponsor>
}
