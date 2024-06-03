package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.model.Contributor
import com.droidknights.app.core.model.ContributorWithYears
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class GetContributorsUseCaseTest : BehaviorSpec() {

    private val useCase: GetContributorsUseCase = GetContributorsUseCase(
        repository = FakeContributorRepository(contributors, contributorsWithYears)
    )

    init {
        Given("드로이드나이츠 컨트리뷰터가 존재한다") {

            When("드로이드나이츠 컨트리뷰터를 조회한다") {
                val contributors: List<Contributor> = useCase.invoke()

                Then("올해 드로이드나이츠 컨트리뷰터를 반환한다") {
                    contributors.size shouldBe 2
                }
            }
        }
    }

    companion object {
        private val contributors = listOf(
            Contributor(
                id = 0L,
                name = "test name",
                imageUrl = "test image url",
                githubUrl = "test github url",
            ),
            Contributor(
                id = 1L,
                name = "test name1",
                imageUrl = "test image url1",
                githubUrl = "test github url1",
            ),
            Contributor(
                id = 2L,
                name = "test name2",
                imageUrl = "test image url2",
                githubUrl = "test github url2",
            )
        )

        private val contributorsWithYears = listOf(
            ContributorWithYears(
                id = 1L,
                listOf(2023, 2024)
            ),
            ContributorWithYears(
                id = 2L,
                listOf(2023)
            )
        )
    }
}
