package com.droidknights.app.core.domain.contributor.usecase

import com.droidknights.app.core.data.contributor.api.ContributorRepository
import com.droidknights.app.core.domain.contributor.usecase.api.GetContributorsUseCase
import com.droidknights.app.core.model.contributor.ContributorGroup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetContributorsUseCaseImpl @Inject constructor(
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
