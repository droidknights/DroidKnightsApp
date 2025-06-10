package com.droidknights.app.core.data.contributor

import com.droidknights.app.core.data.contributor.api.DroidknightsContributorsApi
import com.droidknights.app.core.data.contributor.api.GithubContributorsApi
import com.droidknights.app.core.data.contributor.mapper.toData
import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import com.droidknights.app.core.model.contributor.Contributor
import com.droidknights.app.core.model.contributor.ContributorGroup
import com.droidkniths.app.core.data.contributor.api.ContributorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class ContributorRepositoryImpl(
    private val githubContributorsApi: GithubContributorsApi,
    private val droidknightsContributorsApi: DroidknightsContributorsApi,
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<List<ContributorGroup>> {
        val contributorsFlow = flow {
            emit(githubContributorsApi.getContributors(owner, name))
        }.map { list -> list.map { it.toData() } }

        val yearGroupsFlow = flow {
            emit(droidknightsContributorsApi.getContributorWithYears())
        }.map { responses ->
            buildYearGroups(responses)
        }

        return combine(contributorsFlow, yearGroupsFlow) { contributors, yearGroups ->
            val contributorMap: Map<String, Contributor> =
                contributors.associateBy { it.id.toString() }

            yearGroups.map { (year, contributorIds) ->
                ContributorGroup(
                    year = year,
                    contributors = contributorIds.mapNotNull { id -> contributorMap[id] },
                )
            }
        }
    }

    private fun buildYearGroups(responses: List<ContributionYearResponse>): List<Pair<Int, List<String>>> {
        val yearToIds = mutableMapOf<Int, MutableSet<String>>()

        responses.forEach { response ->
            response.years.forEach { year ->
                yearToIds.getOrPut(year) { mutableSetOf() }
                    .add(response.id.toString()) // year 기준으로 그룹화
            }
        }

        return yearToIds.entries
            .sortedByDescending { it.key }
            .map { (year, ids) -> year to ids.toList() }
    }
}
