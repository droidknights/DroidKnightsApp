package com.droidknights.app.feature.bookmark.model

import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class BookmarkItemUiStateTest {

    @Test
    fun `순서는 인덱스에 1을 더한 값이다`() {
        // given
        val uiState = BookmarkItemUiState(index = 0, session = mockSession)

        // when
        val actual: Int = uiState.sequence

        // then
        assertEquals(expected = 1, actual = actual)
    }

    @Test
    fun `태그 라벨은 세션 태그의 이름들을 , 로 구분하여 반환한다`() {
        // given
        val uiState = BookmarkItemUiState(index = 0, session = mockSession)

        // when
        val actual: String = uiState.tagLabel

        // then
        assertEquals(expected = "교육, 안드로이드, 코틀린", actual = actual)
    }

    @Test
    fun `태그 라벨이 하나라면 그대로 반환한다`() {
        // given
        val uiState = BookmarkItemUiState(index = 1, session = mockSessionWithSingleTagAndLabel)

        // when
        val actual: String = uiState.tagLabel

        // then
        assertEquals(expected = "교육", actual = actual)
    }

    @Test
    fun `스피커 라벨은 스피커들의 이름을 , 로 구분하여 반환한다`() {
        // given
        val uiState = BookmarkItemUiState(index = 0, session = mockSession)

        // when
        val actual: String = uiState.speakerLabel

        // then
        assertEquals(expected = "마틴 파울러, 레아, 반달", actual = actual)
    }

    @Test
    fun `스피커 라벨이 하나라면 그대로 반환한다`() {
        // given
        val uiState = BookmarkItemUiState(index = 1, session = mockSessionWithSingleTagAndLabel)

        // when
        val actual: String = uiState.speakerLabel

        // then
        assertEquals(expected = "마틴 파울러", actual = actual)
    }

    @Test
    fun `시간 객체는 세션의 시작시간을 Java의 LocalTime으로 변환하여 반환한다`() {
        // given
        val uiState = BookmarkItemUiState(index = 1, session = mockSession)

        // when
        val actual: java.time.LocalTime = uiState.time

        // then
        assertIs<kotlinx.datetime.LocalDateTime>(uiState.session.startTime)
        assertIs<java.time.LocalTime>(actual)
        assertEquals(
            expected = kotlinx.datetime.LocalDateTime(
                year = 2025,
                monthNumber = 5,
                dayOfMonth = 12,
                hour = 10,
                minute = 50,
            ),
            actual = uiState.session.startTime
        )
    }

    companion object {
        private val mockSession = Session(
            id = "1",
            title = "세션 제목",
            content = "세션 내용",
            speakers = listOf(
                Speaker(
                    name = "마틴 파울러",
                    introduction = "엉클 밥",
                    imageUrl = "",
                ),
                Speaker(
                    name = "레아",
                    introduction = "안드로이드 교육자",
                    imageUrl = "",
                ),
                Speaker(
                    name = "반달",
                    introduction = "안드로이드 개발자",
                    imageUrl = "",
                ),
            ),
            tags = listOf(
                Tag("교육"),
                Tag("안드로이드"),
                Tag("코틀린"),
            ),
            room = Room.TRACK2,
            startTime = kotlinx.datetime.LocalDateTime(
                year = 2025,
                monthNumber = 5,
                dayOfMonth = 12,
                hour = 10,
                minute = 50,
            ),
            endTime = kotlinx.datetime.LocalDateTime(
                year = 2025,
                monthNumber = 5,
                dayOfMonth = 12,
                hour = 11,
                minute = 30,
            ),
            isBookmarked = false
        )
        private val mockSessionWithSingleTagAndLabel = Session(
            id = "2",
            title = "세션 제목",
            content = "세션 내용",
            speakers = listOf(
                Speaker(
                    name = "마틴 파울러",
                    introduction = "엉클 밥",
                    imageUrl = "",
                ),
            ),
            tags = listOf(
                Tag("교육"),
            ),
            room = Room.TRACK1,
            startTime = kotlinx.datetime.LocalDateTime(
                year = 2025,
                monthNumber = 5,
                dayOfMonth = 12,
                hour = 10,
                minute = 50,
            ),
            endTime = kotlinx.datetime.LocalDateTime(
                year = 2025,
                monthNumber = 5,
                dayOfMonth = 12,
                hour = 11,
                minute = 30,
            ),
            isBookmarked = false
        )
    }
}
