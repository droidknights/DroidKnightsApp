package com.droidknights.app.core.domain.contributor.usecase

import com.droidknights.app.core.domain.contributor.api.GetContributorsUseCase
import com.droidknights.app.core.model.contributor.ContributorGroup
import com.droidkniths.app.core.data.contributor.api.ContributorRepository
import kotlinx.coroutines.flow.Flow

internal class GetContributorsUseCaseImpl(
    private val repository: ContributorRepository,
) : GetContributorsUseCase {

    override operator fun invoke(): Flow<List<ContributorGroup>> =
        repository.flowContributors(
            owner = OWNER,
            name = NAME,
        )

    companion object {

        private const val OWNER = "droidknights"

        private const val NAME = "DroidKnightsApp"
    }
}
