package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.SponsorRepository
import com.droidknights.app2023.core.domain.usecase.api.GetSponsorsUseCase
import com.droidknights.app2023.core.model.Sponsor
import javax.inject.Inject

internal class GetSponsorsUseCaseImpl @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) : GetSponsorsUseCase {

    override suspend operator fun invoke(): List<Sponsor> {
        return sponsorRepository
            .getSponsors()
            .sortedBy { it.grade.priority }
    }
}
