package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorGroup
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first

internal class GetContributorsUseCaseTest : BehaviorSpec() {

    private val useCase: GetContributorsUseCase = GetContributorsUseCase(
        repository = FakeContributorRepository(mockContributors)
    )

    init {
        Given("드로이드나이츠 컨트리뷰터가 존재한다") {

            When("드로이드나이츠 컨트리뷰터를 조회한다") {
                val contributors = useCase.invoke().first()

                Then("연도별 드로이드나이츠 컨트리뷰터를 반환한다") {
                    contributors shouldBe mockContributors
                }
            }
        }
    }

    companion object {

        private val mockContributors = listOf(
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
