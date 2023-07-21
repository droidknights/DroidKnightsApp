package com.droidknights.app2023.core.domain.mapper

import com.droidknights.app2023.core.data.model.ContributorEntity
import com.droidknights.app2023.core.model.Contributor

internal fun ContributorEntity.toDomain(): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl
    )
