package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.ContributorRepository
import com.droidknights.app.core.model.Contributor
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(
    private val repository: ContributorRepository,
) {
    suspend operator fun invoke(): List<Contributor> {
        return repository.getContributors(
            owner = OWNER,
            name = NAME,
        )
    }

    companion object {

        private const val OWNER = "droidknights"

        private const val NAME = "DroidKnightsApp"
    }
}
