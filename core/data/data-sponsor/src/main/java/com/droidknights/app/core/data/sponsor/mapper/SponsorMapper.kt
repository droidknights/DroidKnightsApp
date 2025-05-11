package com.droidknights.app.core.data.sponsor.mapper

import com.droidknights.app.core.data.sponsor.model.SponsorResponse
import com.droidknights.app.core.model.sponsor.Sponsor

internal fun SponsorResponse.toData(): Sponsor =
    Sponsor(
        name = name,
        imageUrl = imageUrl,
        homepage = homepage,
        grade = when (grade) {
            SponsorResponse.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
            SponsorResponse.Grade.GOLD -> Sponsor.Grade.GOLD
            SponsorResponse.Grade.SILVER -> Sponsor.Grade.SILVER
        },
    )
