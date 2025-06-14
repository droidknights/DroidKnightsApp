package com.droidknights.app.feature.session.component

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import kotlinx.datetime.LocalDateTime

internal class SessionPreviewParameterProvider : PreviewParameterProvider<Session> {
    override val values = sequenceOf(
        Session(
            // single speaker with out bookmark
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = false,
        ),
        Session(
            // single speaker with bookmark
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = true,
        ),
        Session(
            // multi speakers
            id = "1",
            title = "Jetpack Compose에 있는 것, 없는 것",
            content = "",
            speakers = listOf(
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
                Speaker(
                    name = "안성용",
                    introduction = "안드로이드 개발자",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf(
                Tag("효율적인 코드베이스"),
                Tag("효율적인 코드베이스"),
                Tag("효율적인 코드베이스")
            ),
            startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
            endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
            room = Room.TRACK1,
            isBookmarked = false,
        ),
    )
}
