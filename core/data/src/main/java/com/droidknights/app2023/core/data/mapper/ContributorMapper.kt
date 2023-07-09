package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.model.ContributorEntity
import com.droidknights.app2023.core.data.api.ContributorResponse

internal fun ContributorResponse.toData(): ContributorEntity =
    ContributorEntity(
        name = this.name,
        imageUrl = this.imageUrl
    )
