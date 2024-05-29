package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.model.Contributor
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class GetContributorsUseCaseTest : BehaviorSpec() {

    private val useCase: GetContributorsUseCase = GetContributorsUseCase(
        repository = FakeContributorRepository(contributors)
    )

    init {
        Given("드로이드나이츠 컨트리뷰터가 존재한다") {
            val expected = contributors

            When("올해 드로이드나이츠 컨트리뷰터를 조회한다") {
                val contributors: List<Contributor> = useCase.invoke()

                Then("드로이드나이츠 컨트리뷰터를 반환한다") {
                    contributors.size shouldBe 3
                    contributors.forEachIndexed { index, contributor ->
                        contributor.name shouldBe expected[index].name
                    }
                }
            }
            When("2023년도 컨트리뷰터를 조회한다.") {
                val contributors: List<Contributor> = useCase.invoke(2023)

                Then("드로이드나이츠 컨트리뷰터를 반환한다") {

                    contributors.size shouldBe 1
                    contributors.all {
                        println(it.name)
                        println(expected[1].name)
                        it.name == expected[1].name
                    }
                }
            }
        }
    }

    companion object {
        private val contributors = listOf(
            Contributor(
                name = "test name",
                imageUrl = "test image url",
                githubUrl = "test github url",
                contributionYears = listOf(2024)
            ),
            Contributor(
                name = "test name2",
                imageUrl = "test image url2",
                githubUrl = "test github url2",
                contributionYears = listOf(2023, 2024)
            ),
            Contributor(
                name = "test name2",
                imageUrl = "test image url3",
                githubUrl = "test github url3",
                contributionYears = listOf()
            )
        )
    }
}
