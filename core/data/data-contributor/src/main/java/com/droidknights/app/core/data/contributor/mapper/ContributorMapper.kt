package com.droidknights.app.core.data.contributor.mapper

import com.droidknights.app.core.data.contributor.model.ContributorResponse
import com.droidknights.app.core.model.Contributor

internal fun ContributorResponse.toData(): Contributor =
    Contributor(
        id = id,
        name = name,
        imageUrl = imageUrl,
        githubUrl = githubUrl,
    )
