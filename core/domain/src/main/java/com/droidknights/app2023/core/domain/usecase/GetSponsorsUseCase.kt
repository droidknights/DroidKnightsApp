package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SponsorRepository
import com.droidknights.app2023.core.model.Sponsor
import javax.inject.Inject

class GetSponsorsUseCase @Inject constructor(
    private val sponsorRepository: SponsorRepository,
) {

    // TODO: Sponsor 데이터 연결

    suspend operator fun invoke(): List<Sponsor> {
        return listOf(
            Sponsor(
                name = "Sponsor1",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.GOLD,
                imageUrl = "https://picsum.photos/id/237/200/200",
            ),
            Sponsor(
                name = "Sponsor2",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/204/200/200",
            ),
            Sponsor(
                name = "Sponsor3",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/203/200/200",
            ),
            Sponsor(
                name = "Sponsor4",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/202/200/200",
            ),
            Sponsor(
                name = "Sponsor5",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/201/200/200",
            ),
            Sponsor(
                name = "Sponsor6",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/200/200/200",
            ),
        )
    }
}
