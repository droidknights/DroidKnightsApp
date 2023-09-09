package com.droidknights.app2023.feature.home.usecase

import com.droidknights.app2023.core.sponsor.repo.api.SponsorRepository
import com.droidknights.app2023.core.sponsor.repo.api.model.Sponsor
import javax.inject.Inject

internal interface GetSponsorsUseCase {
    suspend operator fun invoke(): List<Sponsor>
}

internal class GetSponsorsUseCaseImpl @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) : GetSponsorsUseCase {

    override suspend operator fun invoke(): List<Sponsor> {
        return sponsorRepository
            .getSponsors()
            .sortedBy { it.grade.priority }
    }
}
