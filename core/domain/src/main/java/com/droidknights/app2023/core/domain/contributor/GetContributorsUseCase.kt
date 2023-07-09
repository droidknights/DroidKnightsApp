package com.droidknights.app2023.core.domain.contributor

import com.droidknights.app2023.core.data.model.ContributorEntity
import com.droidknights.app2023.core.data.repository.ContributorRepository
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(
    private val repository: ContributorRepository,
) {
    suspend operator fun invoke(): List<Contributor> {
        return repository.getContributors(
            owner = OWNER,
            name = NAME,
        ).map(ContributorEntity::toDomain)
    }

    companion object {
        private const val OWNER = "droidknights"
        // TODO: DroidKnights2023_App로 변경 필요
        private const val NAME = "DroidKnights2021_App"
    }
}
