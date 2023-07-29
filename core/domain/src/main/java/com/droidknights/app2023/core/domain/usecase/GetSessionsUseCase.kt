package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.model.Speaker
import com.droidknights.app2023.core.model.Tag
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class GetSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    /**
     * TODO: 세션 데이터 연결
     */
    suspend operator fun invoke(): List<Session> {
        return listOf(
            Session(
                title = "Jetpack Compose에 있는 것, 없는 것",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "안성용",
                        imageUrl = "https://picsum.photos/260",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("효율적인 코드베이스")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
                endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
                room = Room.TRACK1,
            ),
            Session(
                title = "Asynchronous Programming for Android",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "안성용",
                        imageUrl = "https://picsum.photos/253",
                    ),
                    Speaker(
                        name = "권혁신",
                        imageUrl = "https://picsum.photos/219",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("Android honey tips")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 15, 0),
                room = Room.TRACK1,
            ),
            Session(
                title = "안드로이드 앱에서 Koin걷어내고 Hilt로 마이그레이션하기",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "이기정",
                        imageUrl = "https://picsum.photos/203",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("효율적인 코드 베이스")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 15, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 45, 0),
                room = Room.TRACK2,
            ),
            Session(
                title = "Asynchronous Programming for Android",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "권혁신",
                        imageUrl = "https://picsum.photos/205",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("Android honey tips")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 15, 0),
                room = Room.TRACK2,
            ),
            Session(
                title = "안드로이드 앱에서 Koin걷어내고 Hilt로 마이그레이션하기",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "이기정",
                        imageUrl = "https://picsum.photos/208",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("효율적인 코드 베이스")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 15, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 45, 0),
                room = Room.TRACK3,
            ),
            Session(
                title = "Asynchronous Programming for Android",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "권혁신",
                        imageUrl = "https://picsum.photos/210",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("Android honey tips")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 15, 0),
                room = Room.TRACK3,
            ),
            Session(
                title = "Asynchronous Programming for Android",
                content = emptyList(),
                speakers = listOf(
                    Speaker(
                        name = "권혁신",
                        imageUrl = "https://picsum.photos/210",
                    ),
                ),
                level = Level.BASIC,
                tags = listOf(
                    Tag("Android honey tips")
                ),
                startTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
                endTime = LocalDateTime(2023, 9, 12, 17, 15, 0),
                room = Room.TRACK3,
            ),
        )
    }
}
