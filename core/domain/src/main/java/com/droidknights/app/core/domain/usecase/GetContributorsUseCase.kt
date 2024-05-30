package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(
    private val repository: ContributorRepository,
) {
    suspend operator fun invoke(): List<Contributor> {
        val contributors = repository.getContributors(
            owner = OWNER,
            name = NAME,
        )
        val contributorsWithYears = repository.getContributorsWithYears()

        val contributionYears = contributorsWithYears.flatMap { it.years }.toSet().sorted()
        val latestYear = contributionYears.last()

        return contributors.filter { contributor ->
            val years = contributorsWithYears
                .find { it.id == contributor.id }
                ?.years ?: listOf(latestYear)

            years.contains(latestYear)
        }
    }

    companion object {

        private const val OWNER = "droidknights"

        private const val NAME = "DroidKnightsApp"
    }
}
