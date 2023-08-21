package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.api.model.ContributorResponse
import com.droidknights.app2023.core.model.Contributor

internal fun ContributorResponse.toData(): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl,
        githubUrl = this.githubUrl,
    )
