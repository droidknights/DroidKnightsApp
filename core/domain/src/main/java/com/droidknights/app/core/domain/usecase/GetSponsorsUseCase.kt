package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.SponsorRepository
import com.droidknights.app.core.model.Sponsor
import javax.inject.Inject

class GetSponsorsUseCase @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) {

    suspend operator fun invoke(): List<Sponsor> =
        sponsorRepository
            .getSponsors()
            .sortedBy { it.grade.priority }
}
