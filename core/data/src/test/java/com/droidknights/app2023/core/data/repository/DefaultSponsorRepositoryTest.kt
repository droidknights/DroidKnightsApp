package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.fake.FakeGithubRawApi
import com.droidknights.app2023.core.data.model.SponsorEntity
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json

internal class DefaultSponsorRepositoryTest : StringSpec() {
    
    init {
        val repository: SponsorRepository = DefaultSponsorRepository(
            githubRawApi = FakeGithubRawApi(
                json = Json { ignoreUnknownKeys = true },
            )
        )
        "역직렬화 테스트" {
            val expected = SponsorEntity(
                name = "헤이딜러",
                imageUrl = "https://raw.githubusercontent.com/droidknights/DroidKnights2020_App/master/androidapp/app/src/main/res/drawable-xxxhdpi/ic_sponsor_heydealer.png",
                homepage = "https://heydealer.co.kr/",
                grade = SponsorEntity.Grade.GOLD,
            )
            val actual = repository.getSponsors().first()
            actual shouldBe expected
        }
    }
}
