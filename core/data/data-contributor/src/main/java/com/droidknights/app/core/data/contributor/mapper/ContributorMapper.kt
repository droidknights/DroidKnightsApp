package com.droidknights.app.core.data.contributor.mapper

import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import com.droidknights.app.core.model.contributor.Contributor

internal fun ContributionYearResponse.toData(): Contributor =
    Contributor(
        id = id,
        name = login,
    )
