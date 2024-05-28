package com.droidknights.app.core.data.repository

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.mapper.toData
import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.Contributor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultContributorRepository @Inject constructor(
    private val githubApi: GithubApi,
) : ContributorRepository {

    override suspend fun getContributors(
        owner: String,
        name: String,
    ): List<Contributor> {
        val contributors = githubApi.getContributors(owner, name)
        return withContext(Dispatchers.IO) {
            contributors.map { contributor ->
                async {
                    val commits =
                        githubApi.getCommits(owner, name, contributor.name, COUNT_FOR_LAST_COMMIT)
                    if (commits.any { commit -> commit.commit.author.date.startsWith("2024") }) {
                        contributor.toData()
                    } else {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
        }
    }

    companion object {
        private const val COUNT_FOR_LAST_COMMIT = 1
    }
}

