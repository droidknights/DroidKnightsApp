package com.droidknights.app.core.data.repository

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.mapper.toData
import com.droidknights.app.core.data.repository.api.ContributorRepository
import com.droidknights.app.core.model.ContributorGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultContributorRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRawApi: GithubRawApi
) : ContributorRepository {

    override fun flowContributors(owner: String, name: String): Flow<List<ContributorGroup>> =
        combine(
            flow {
                emit(githubApi.getContributors(owner, name))
            }
                .map { list ->
                    list.map { it.toData() }
                },
            flow {
                emit(githubRawApi.getContributorWithYears())
            }
                .map { list ->
                    list
                        .flatMap { data ->
                            data.years.map { year -> year to data.id }
                        }
                        .groupBy { it.first } // year 기준으로 그룹화
                        .toSortedMap()
                        .mapValues {
                            it.value.distinctBy { it.second }.map { it.second }
                        } // 각 그룹의 id 리스트 만들고 중복 제거
                        .toMap()
                        .toSortedMap(compareByDescending { it })
                }
        ) { contributors, yearMap ->
            // id를 기반으로 두 리스트 매칭
            val resultMap = contributors.associateBy { it.id }

            // map1의 각 키에 해당하는 데이터 리스트 추출 및 Data 객체로 변환
            yearMap.mapValues { year ->
                year.value.mapNotNull { id -> resultMap[id] }
            }
            yearMap.map { year ->
                ContributorGroup(
                    year = year.key,
                    contributors = year.value.mapNotNull { id -> resultMap[id] },
                )
            }
        }
}
