package com.droidknights.app.core.data.contributor

import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.data.contributor.api.ContributorRepository
import com.droidknights.app.core.data.contributor.api.DroidnightsContributorsApi
import com.droidknights.app.core.data.contributor.mapper.toData
import com.droidknights.app.core.model.contributor.ContributorGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ContributorRepositoryImpl @Inject constructor(
    private val droidnightsContributorsApi: DroidnightsContributorsApi,
    private val droidknightsBuildConfig: DroidknightsBuildConfig,
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<List<ContributorGroup>> =
        flow {
            emit(droidnightsContributorsApi.getContributorWithYears(url = droidknightsBuildConfig.contributorsDataUrl()))
        }
            .map { list ->
                list
                    .flatMap { data ->
                        data.years.map { year -> year to data }
                    }
                    .groupBy { it.first } // year 기준으로 그룹화
                    .toSortedMap()
                    .mapValues {
                        it.value.distinctBy { it.second }.map { it.second }
                    } // 각 그룹의 id 리스트 만들고 중복 제거
                    .toMap()
                    .toSortedMap(compareByDescending { it })
            }
            .map { yearMap ->
                yearMap.map { year ->
                    ContributorGroup(
                        year = year.key,
                        contributors = year.value.map {
                            it.toData()
                        },
                    )
                }
            }
}
