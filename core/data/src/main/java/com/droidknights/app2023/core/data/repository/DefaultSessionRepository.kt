package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.mapper.toData
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

internal class DefaultSessionRepository @Inject constructor(
    private val githubRawApi: GithubRawApi,
) : SessionRepository {

    override suspend fun getSessions(): List<Session> {
        return githubRawApi.getSessions().map { it.toData() }
    }
}
