package com.droidknights.app2023.core.domain.usecase.api

import com.droidknights.app2023.core.model.Sponsor

interface GetSponsorsUseCase {
    suspend operator fun invoke(): List<Sponsor>
}
