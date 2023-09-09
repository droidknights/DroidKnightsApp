package com.droidknights.app2023.core.sponsor.repo.mapper

import com.droidknights.app2023.core.sponsor.repo.api.model.Sponsor
import com.droidknights.app2023.core.sponsor.repo.network.model.SponsorResponse

internal fun SponsorResponse.toData(): Sponsor = Sponsor(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = when (grade) {
        SponsorResponse.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
        SponsorResponse.Grade.GOLD -> Sponsor.Grade.GOLD
    }
)
