package com.droidknights.app.core.domain.sponsor.usecase.api

import com.droidknights.app.core.model.sponsor.Sponsor

interface GetSponsorsUseCase {

    suspend operator fun invoke(): List<Sponsor>
}
