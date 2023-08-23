package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.model.Contributor
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class GetContributorsUseCaseTest : BehaviorSpec() {

    private val useCase: GetContributorsUseCase = GetContributorsUseCase(
        repository = FakeContributorRepository(contributors)
    )

    init {
        Given("드로이드나이츠 컨트리뷰터가 존재한다") {
            val expected = contributors

            When("드로이드나이츠 컨트리뷰터를 조회한다") {
                val contributors: List<Contributor> = useCase.invoke()

                Then("드로이드나이츠 컨트리뷰터를 반환한다") {
                    contributors.size shouldBe 1
                    contributors.all {
                        it.name == expected[0].name
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
                githubUrl = "test github url"
            )
        )
    }
}
