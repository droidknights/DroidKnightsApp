package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.fake.FakeGithubRawApi
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDateTime

internal class DefaultSessionRepositoryTest : StringSpec() {

    init {
        val repository: SessionRepository = DefaultSessionRepository(
            githubRawApi = FakeGithubRawApi()
        )
        "역직렬화 테스트" {
            val expected = Session(
                id = "1",
                title = "Keynote",
                content = "",
                speakers = emptyList(),
                level = Level.ETC,
                tags = emptyList(),
                room = Room.ETC,
                startTime = LocalDateTime(2023, 9, 12, 11, 0),
                endTime = LocalDateTime(2023, 9, 12, 11, 15),
            )
            val actual = repository.getSessions()
            actual.first() shouldBe expected
        }
    }
}
