package com.droidknights.app.core.data.sponsor

import com.droidknights.app.core.data.sponsor.api.SponsorRepository
import com.droidknights.app.core.data.sponsor.fake.FakeDroidknightsBuildConfig
import com.droidknights.app.core.data.sponsor.fake.FakeSponsorApi
import com.droidknights.app.core.model.sponsor.Sponsor
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json

internal class SponsorRepositoryImplTest : StringSpec() {

    init {
        val repository: SponsorRepository = SponsorRepositoryImpl(
            sponsorApi = FakeSponsorApi(
                json = Json { ignoreUnknownKeys = true },
            ),
            droidknightsBuildConfig = FakeDroidknightsBuildConfig(),
        )
        "역직렬화 테스트" {
            val expected = Sponsor(
                name = "젯브레인",
                imageUrl = "https://raw.githubusercontent.com/droidknights/DroidKnightsApp/main/feature/home/src/main/res/drawable/img_sponsor_jetbrains.png",
                homepage = "http://www.jetbrains.com/ko-kr/",
                grade = Sponsor.Grade.GOLD,
            )
            val actual = repository.getSponsors().first()
            actual shouldBe expected
        }
    }
}
