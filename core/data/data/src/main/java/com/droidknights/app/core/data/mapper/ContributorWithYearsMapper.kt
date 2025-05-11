package com.droidknights.app.core.data.mapper

import com.droidknights.app.core.data.api.model.ContributionYearResponse
import com.droidknights.app.core.model.ContributorWithYears

internal fun ContributionYearResponse.toData(): ContributorWithYears {
    return ContributorWithYears(
        id = id,
        years = years,
    )
}