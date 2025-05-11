package com.droidknights.app.core.domain.sponsor

import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.domain.sponsor.api.GetSponsorsUseCase
import com.droidknights.app.core.model.Sponsor
import javax.inject.Inject

class GetSponsorsUseCaseImpl @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) : GetSponsorsUseCase {

    override suspend operator fun invoke(): List<Sponsor> =
        sponsorRepository
            .getSponsors()
            .sortedBy { it.grade.priority }
}
