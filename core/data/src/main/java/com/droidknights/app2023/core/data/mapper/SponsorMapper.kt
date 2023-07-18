package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.api.model.SponsorResponse
import com.droidknights.app2023.core.data.model.SponsorEntity

internal fun SponsorResponse.toData(): SponsorEntity = SponsorEntity(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = when (grade) {
        SponsorResponse.Grade.PLATINUM -> SponsorEntity.Grade.PLATINUM
        SponsorResponse.Grade.GOLD -> SponsorEntity.Grade.GOLD
    }
)
