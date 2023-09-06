package com.droidknights.app2023.core.domain.usecase.api

import com.droidknights.app2023.core.model.Contributor

interface GetContributorsUseCase {
    suspend operator fun invoke(): List<Contributor>
}
