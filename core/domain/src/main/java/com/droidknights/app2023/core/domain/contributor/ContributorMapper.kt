package com.droidknights.app2023.core.domain.contributor

import com.droidknights.app2023.core.data.model.ContributorEntity

internal fun ContributorEntity.toDomain(): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl
    )
