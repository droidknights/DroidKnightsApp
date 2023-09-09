package com.droidknights.app2023.feature.contributor.usecase

import com.droidknights.app2023.core.repo.contributor.api.model.Contributor
import com.droidknights.app2023.core.repo.contributor.api.ContributorRepository
import javax.inject.Inject

internal interface GetContributorsUseCase {
    suspend operator fun invoke(): List<Contributor>
}
internal class GetContributorsUseCaseImpl @Inject constructor(
    private val repository: ContributorRepository,
) : GetContributorsUseCase {
    override suspend operator fun invoke(): List<Contributor> {
        return repository.getContributors(
            owner = OWNER,
            name = NAME,
        )
    }

    companion object {
        private const val OWNER = "droidknights"
        private const val NAME = "DroidKnights2023_App"
    }
}
