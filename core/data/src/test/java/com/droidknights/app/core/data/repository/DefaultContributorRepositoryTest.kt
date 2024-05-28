package com.droidknights.app.core.data.repository

import com.droidknights.app.core.data.api.fake.FakeGithubApi
import com.droidknights.app.core.data.api.model.Author
import com.droidknights.app.core.data.api.model.AuthorInfo
import com.droidknights.app.core.data.api.model.Commit
import com.droidknights.app.core.data.api.model.CommitResponse
import com.droidknights.app.core.data.api.model.ContributorResponse
import com.droidknights.app.core.model.Contributor
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DefaultContributorRepositoryTest : BehaviorSpec() {

    private val repository: DefaultContributorRepository = DefaultContributorRepository(
        githubApi = FakeGithubApi(contributors, commits)
    )

    init {
        Given("컨트리뷰터가 존재한다") {
            val expected = contributors

            When("컨트리뷰터를 조회한다") {
                val contributors: List<Contributor> = repository.getContributors(
                    owner = "droidknights", name = "app"
                )
                Then("컨트리뷰터를 반환한다") {
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
            ContributorResponse(
                name = "test name", imageUrl = "test image url", githubUrl = "test github url"
            )
        )

        private val commits = listOf(
            CommitResponse(
                commit = Commit(
                    author = Author(
                        name = "Helena Suarez",
                        email = "britney.mathis@example.com",
                        date = "2024/05/31"
                    )

                ),
                author = AuthorInfo(
                    name = "Clair Pollard",
                    imageUrl = "https://search.yahoo.com/search?p=sed",
                    githubUrl = "http://www.bing.com/search?q=regione"
                )
            )
        )
    }
}
