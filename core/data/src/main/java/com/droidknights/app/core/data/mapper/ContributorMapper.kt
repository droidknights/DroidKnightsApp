package com.droidknights.app.core.data.mapper

import com.droidknights.app.core.data.api.model.ContributorResponse
import com.droidknights.app.core.model.Contributor

internal fun ContributorResponse.toData(contributeYears: List<Int>): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl,
        githubUrl = this.githubUrl,
        contributionYears = contributeYears,
    )
