package com.droidknights.app2023.core.repo.contributor.mapper

import com.droidknights.app2023.core.repo.contributor.api.model.Contributor
import com.droidknights.app2023.core.repo.contributor.network.model.ContributorResponse

internal fun ContributorResponse.toData(): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl,
        githubUrl = this.githubUrl,
    )
