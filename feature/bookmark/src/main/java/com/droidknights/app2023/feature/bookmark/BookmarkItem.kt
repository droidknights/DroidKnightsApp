package com.droidknights.app2023.feature.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LightGray
import com.droidknights.app2023.core.model.Room
import java.time.LocalTime

/**
 * @param leadingContent 시작지점인 왼쪽에 표현될 컨텐츠
 * @param midContent 가운테 표현될 컨텐츠
 * @param trailingContent 끝에 표현될 컨텐츠
 * @param leadingContentWidth leadingContet의 너비 (TimeLine Stroke 위치 잡는데 필요합니다)
 * @param leadingContentHeight leadingContet의 너비 (TimeLine Stroke 위치 잡는데 필요합니다)
 */
@Composable
internal fun BookmarkItem(
    leadingContent: @Composable () -> Unit,
    midContent: @Composable () -> Unit,
    trailingContent: @Composable () -> Unit,
    isShowTrailingContent: Boolean,
    leadingContentWidth: Dp = 44.dp,
    leadingContentHeight: Dp = 58.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = LightGray,
                    start = Offset(x = leadingContentWidth.toPx() / 2, y = 0F),
                    end = Offset(x = leadingContentWidth.toPx() / 2, y = (this.size.height / 2) - (leadingContentHeight.toPx() / 2)),
                    strokeWidth = 1.dp.toPx()
                )

                drawLine(
                    color = LightGray,
                    start = Offset(x = leadingContentWidth.toPx() / 2, y = (this.size.height / 2) + (leadingContentHeight.toPx() / 2)),
                    end = Offset(x = leadingContentWidth.toPx() / 2, y = this.size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingContent()

            Box(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1F)
            ) {
                midContent()
            }

            AnimatedVisibility(
                visible = isShowTrailingContent,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it }
            ) {
                trailingContent()
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F9F9)
@Composable
private fun BookmarkItemPreview() {
    KnightsTheme {
        Column {
            BookmarkItem(
                leadingContent = @Composable {
                    BookmarkTimelineItem(
                        sequence = 1,
                        time = LocalTime.of(1, 20)
                    )
                },
                midContent = @Composable {
                    BookmarkCard(
                        tagLabel = "효율적인 코드 베이스",
                        room = Room.TRACK2,
                        title = "Jetpack Compose에 있는 것, 없는것",
                        speaker = "홍길동"
                    )
                },
                isShowTrailingContent = false,
                trailingContent = {}
            )

            BookmarkItem(
                leadingContent = @Composable {
                    BookmarkTimelineItem(
                        sequence = 2,
                        time = LocalTime.of(1, 20)
                    )
                },
                midContent = @Composable {
                    BookmarkCard(
                        tagLabel = "효율적인 코드 베이스",
                        room = Room.TRACK2,
                        title = "Jetpack Compose에 있는 것, 없는것",
                        speaker = "홍길동"
                    )
                },
                isShowTrailingContent = false,
                trailingContent = {}
            )
        }
    }
}
