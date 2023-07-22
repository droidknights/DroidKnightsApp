package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.api.model.SponsorResponse
import com.droidknights.app2023.core.model.Sponsor

internal fun SponsorResponse.toData(): Sponsor = Sponsor(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = when (grade) {
        SponsorResponse.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
        SponsorResponse.Grade.GOLD -> Sponsor.Grade.GOLD
    }
)
