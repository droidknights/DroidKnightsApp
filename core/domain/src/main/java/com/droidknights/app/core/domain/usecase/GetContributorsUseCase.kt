package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(
    private val repository: ContributorRepository,
) {
    suspend operator fun invoke(year: Int = 2024): List<Contributor> {
        return repository.getContributors(
            owner = OWNER,
            name = NAME,
        ).filter { contributor ->
            when (year) {
                2023 -> contributor.contributionYears.contains(year)
                else -> contributor.contributionYears.contains(year)
                        || contributor.contributionYears.isEmpty()
            }
        }
    }

    companion object {

        private const val OWNER = "droidknights"

        private const val NAME = "DroidKnightsApp"
    }
}
