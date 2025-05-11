package com.droidknights.app.core.data.session

import app.cash.turbine.test
import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.data.session.fake.FakeDroidknightsBuildConfig
import com.droidknights.app.core.data.session.fake.FakeSessionApi
import com.droidknights.app.core.data.session.fake.FakeSessionPreferencesDataSource
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDateTime

class SessionRepositoryImplTest : StringSpec() {

    init {
        val repository: SessionRepository = SessionRepositoryImpl(
            sessionApi = FakeSessionApi(),
            sessionDataSource = FakeSessionPreferencesDataSource(),
            droidknightsBuildConfig = FakeDroidknightsBuildConfig(),
        )
        "역직렬화 테스트" {
            val expected = Session(
                id = "1",
                title = "키노트",
                content = "",
                speakers = emptyList(),
                tags = emptyList(),
                room = Room.ETC,
                startTime = LocalDateTime(2025, 6, 17, 10, 40),
                endTime = LocalDateTime(2025, 6, 17, 11, 0),
                isBookmarked = false
            )
            val actual = repository.getSessions()
            actual.first() shouldBe expected
        }

        "북마크 추가 테스트" {
            repository.getBookmarkedSessionIds().test {
                awaitItem() shouldBe emptySet()

                repository.bookmarkSession(sessionId = "1", bookmark = true)
                awaitItem() shouldBe setOf("1")

                repository.bookmarkSession(sessionId = "2", bookmark = true)
                awaitItem() shouldBe setOf("1", "2")
            }
        }

        "북마크 제거 테스트" {
            // given : [1, 2, 3]
            val bookmarkedSessionIds = listOf("1", "2", "3")
            bookmarkedSessionIds.forEach {
                repository.bookmarkSession(it, true)
            }

            repository.getBookmarkedSessionIds().test {
                awaitItem() shouldBe setOf("1", "2", "3")

                // [1, 2, 3] -> [1, 3]
                repository.bookmarkSession(sessionId = "2", bookmark = false)
                awaitItem() shouldBe setOf("1", "3")

                // [1, 3] -> [1]
                repository.bookmarkSession(sessionId = "3", bookmark = false)
                awaitItem() shouldBe setOf("1")
            }
        }

        "북마크 일괄 제거 테스트" {
            // given : [1, 2, 3, 4]
            val bookmarkedSessionIds = listOf("1", "2", "3", "4")
            bookmarkedSessionIds.forEach {
                repository.bookmarkSession(it, true)
            }

            repository.getBookmarkedSessionIds().test {
                awaitItem() shouldBe setOf("1", "2", "3", "4")

                // [1, 2, 3, 4] -> [1, 3, 4]
                repository.deleteBookmarkedSessions(setOf("2"))
                awaitItem() shouldBe setOf("1", "3", "4")

                // [1, 3, 4] -> [1]
                repository.deleteBookmarkedSessions(setOf("3", "4"))
                awaitItem() shouldBe setOf("1")
            }
        }
    }
}
