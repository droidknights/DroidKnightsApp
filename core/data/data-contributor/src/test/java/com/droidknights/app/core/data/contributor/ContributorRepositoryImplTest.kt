package com.droidknights.app.core.data.contributor

import com.droidknights.app.core.data.contributor.api.fake.FakeDroidknightsBuildConfig
import com.droidknights.app.core.data.contributor.api.fake.FakeDroidnightsContributorsApi
import com.droidknights.app.core.data.contributor.api.fake.FakeGithubContributorsApi
import com.droidknights.app.core.data.contributor.model.ContributorResponse
import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorGroup
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first

internal class ContributorRepositoryImplTest : BehaviorSpec() {

    private val repository: ContributorRepositoryImpl = ContributorRepositoryImpl(
        githubContributorsApi = FakeGithubContributorsApi(contributors),
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
                    contributorList.size shouldBe 2
                    contributorList shouldBe listOf(
                        ContributorGroup(
                            year = 2024,
                            contributors = listOf(
                                Contributor(
                                    name = "2024 - name",
                                    imageUrl = "test image url",
                                    githubUrl = "test github url",
                                    id = 32327475
                                ),
                            ),
                        ),
                        ContributorGroup(
                            year = 2023,
                            contributors = listOf(
                                Contributor(
                                    name = "test name",
                                    imageUrl = "test image url",
                                    githubUrl = "test github url",
                                    id = 28249981
                                ),
                                Contributor(
                                    name = "2024 - name",
                                    imageUrl = "test image url",
                                    githubUrl = "test github url",
                                    id = 32327475
                                ),
                            ),
                        ),
                    )
                }
            }
        }
    }

    companion object {

        private val contributors = listOf(
            ContributorResponse(
                name = "test name",
                imageUrl = "test image url",
                githubUrl = "test github url",
                id = 28249981
            ),
            ContributorResponse(
                name = "2024 - name",
                imageUrl = "test image url",
                githubUrl = "test github url",
                id = 32327475
            ),
        )
    }
}
