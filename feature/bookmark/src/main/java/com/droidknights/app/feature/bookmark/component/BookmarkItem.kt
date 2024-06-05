package com.droidknights.app.feature.bookmark.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.Room
import com.droidknights.app.feature.bookmark.R
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
    isEditMode: Boolean,
    modifier: Modifier = Modifier,
    leadingContentWidth: Dp = 60.dp,
    leadingContentHeight: Dp = 58.dp,
) {
    val lineColor = MaterialTheme.colorScheme.outline

    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                if (isEditMode) return@drawBehind

                drawLine(
                    color = lineColor,
                    start = Offset(x = leadingContentWidth.toPx() / 2, y = 0F),
                    end = Offset(
                        x = leadingContentWidth.toPx() / 2,
                        y = (this.size.height / 2) - (leadingContentHeight.toPx() / 2)
                    ),
                    strokeWidth = 1.dp.toPx()
                )

                drawLine(
                    color = lineColor,
                    start = Offset(
                        x = leadingContentWidth.toPx() / 2,
                        y = (this.size.height / 2) + (leadingContentHeight.toPx() / 2)
                    ),
                    end = Offset(x = leadingContentWidth.toPx() / 2, y = this.size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
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
                visible = isEditMode,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it }
            ) {
                trailingContent()
            }
        }
    }
}

/**
 * Preview 출력용으로만 사용하세요.
 */
@Composable
private fun BookMarkItemForPreview(isEditMode: Boolean) {
    BookmarkItem(
        modifier = Modifier.padding(end = 16.dp),
        leadingContent = @Composable {
            if (isEditMode) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = CircleShape
                        )
                )
            } else {
                BookmarkTimelineItem(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    sequence = 2,
                    time = LocalTime.of(1, 20)
                )
            }
        },
        midContent = @Composable {
            BookmarkCard(
                tagLabel = "효율적인 코드 베이스",
                room = Room.TRACK2,
                title = "Jetpack Compose에 있는 것, 없는것",
                speaker = "홍길동"
            )
        },
        isEditMode = isEditMode,
        trailingContent = {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(id = R.string.drag_and_drop)
            )
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F9F9)
@Composable
private fun BookmarkItemPreview() {
    KnightsTheme {
        Column {
            BookMarkItemForPreview(isEditMode = true)
            BookMarkItemForPreview(isEditMode = false)
        }
    }
}
