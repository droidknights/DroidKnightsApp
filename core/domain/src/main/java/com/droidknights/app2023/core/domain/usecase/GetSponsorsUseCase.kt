package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SponsorRepository
import com.droidknights.app2023.core.model.Sponsor
import javax.inject.Inject

class GetSponsorsUseCase @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) {

    suspend operator fun invoke(): List<Sponsor> {
        return sponsorRepository.getSponsors()
    }
}
