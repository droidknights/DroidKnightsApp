package com.droidknights.app2023.core.domain.mapper

import com.droidknights.app2023.core.data.model.SponsorEntity
import com.droidknights.app2023.core.domain.model.Sponsor

internal fun SponsorEntity.toDomain(): Sponsor = Sponsor(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = when (grade) {
        SponsorEntity.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
        SponsorEntity.Grade.GOLD -> Sponsor.Grade.GOLD
    }
)
