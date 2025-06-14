package com.droidknights.app.core.domain.sponsor.usecase

import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.domain.sponsor.usecase.api.GetSponsorsUseCase
import com.droidknights.app.core.model.sponsor.Sponsor
import javax.inject.Inject

internal class GetSponsorsUseCaseImpl @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) : GetSponsorsUseCase {

    override suspend operator fun invoke(): List<Sponsor> =
        sponsorRepository
            .getSponsors()
            .sortedBy { it.grade.priority }
}
