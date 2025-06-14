package com.droidknights.app.core.data.contributor

import com.droidknights.app.core.data.contributor.api.fake.FakeDroidknightsBuildConfig
import com.droidknights.app.core.data.contributor.api.fake.FakeDroidnightsContributorsApi
import com.droidknights.app.core.model.contributor.Contributor
import com.droidknights.app.core.model.contributor.ContributorGroup
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first

internal class ContributorRepositoryImplTest : BehaviorSpec() {

    private val repository: ContributorRepositoryImpl = ContributorRepositoryImpl(
        droidnightsContributorsApi = FakeDroidnightsContributorsApi(),
        droidknightsBuildConfig = FakeDroidknightsBuildConfig(),
    )

    init {
        Given("컨트리뷰터가 존재한다") {
            When("컨트리뷰터를 조회한다") {
                val contributorList = repository.flowContributors(
                    owner = "droidknights",
                    name = "app",
                ).first()
                Then("컨트리뷰터를 반환한다") {
                    contributorList.size shouldBe 3
                    contributorList shouldBe listOf(
                        ContributorGroup(
                            year = 2025,
                            contributors = listOf(
                                Contributor(
                                    name = "a",
                                    id = 1234
                                ),
                            ),
                        ),
                        ContributorGroup(
                            year = 2024,
                            contributors = listOf(
                                Contributor(
                                    name = "b",
                                    id = 122
                                ),
                            ),
                        ),
                        ContributorGroup(
                            year = 2023,
                            contributors = listOf(
                                Contributor(
                                    name = "a",
                                    id = 1234
                                ),
                                Contributor(
                                    name = "b",
                                    id = 122
                                ),
                            ),
                        ),
                    )
                }
            }
        }
    }
}
