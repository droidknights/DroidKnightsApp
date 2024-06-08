package com.droidknights.app.feature.session.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.ui.RoomText
import com.droidknights.app.feature.session.R
import com.droidknights.app.feature.session.model.SessionState
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

@Composable
internal fun SessionTopAppBar(
    sessionState: SessionState,
    onBackClick: () -> Unit,
) {
    val enter = fadeIn()
    val exit = fadeOut()

    val rooms = sessionState.rooms
    val coroutineScope = rememberCoroutineScope()

    Box {
        if (rooms.isNotEmpty()) {
            AnimatedVisibility(
                visible = !sessionState.isAtTop,
                enter = enter,
                exit = exit,
            ) {
                SessionTabRow(
                    selectedRoom = sessionState.selectedRoom,
                    rooms = rooms.toPersistentList(),
                    onRoomSelect = { room ->
                        coroutineScope.launch {
                            sessionState.scrollTo(room)
                        }
                    },
                    modifier = Modifier.statusBarsPadding(),
                )
            }
        }
        AnimatedVisibility(
            visible = sessionState.isAtTop,
            enter = enter,
            exit = exit,
        ) {
            KnightsTopAppBar(
                titleRes = R.string.session_title,
                navigationType = TopAppBarNavigationType.Close,
                navigationIconContentDescription = null,
                modifier = Modifier.statusBarsPadding(),
                onNavigationClick = onBackClick,
            )
        }
    }
}

@Composable
private fun SessionTabRow(
    selectedRoom: Room?,
    rooms: PersistentList<Room>,
    onRoomSelect: (Room) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        mutableStateListOf<Dp>().apply { addAll(rooms.map { 0.dp }) }
    }
    val selectedTabIndex = rooms.indexOf(selectedRoom)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { tabPositions ->
            TabIndicator(
                height = 3.dp,
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        },
        divider = { HorizontalDivider(color = MaterialTheme.colorScheme.outline) }
    ) {
        rooms.forEachIndexed { tabIndex, room ->
            Tab(
                room = room,
                selected = selectedRoom == room,
                onClick = { onRoomSelect(room) },
                onTextLayout = { textLayoutResult ->
                    tabWidths[tabIndex] = with(density) { textLayoutResult.size.width.toDp() }
                }
            )
        }
    }
}

@Composable
private fun TabIndicator(height: Dp, modifier: Modifier) {
    val brush = SolidColor(MaterialTheme.colorScheme.onSurface)
    Box(
        modifier = modifier
            .height(height * 2)
            .offset { IntOffset(0, height.roundToPx()) }
            .background(brush, RoundedCornerShape(100.dp, 100.dp, 0.dp, 0.dp))
    )
}

@Composable
private fun Tab(
    room: Room,
    selected: Boolean,
    onClick: () -> Unit,
    onTextLayout: (TextLayoutResult) -> Unit,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        text = {
            RoomText(
                room = room,
                style = KnightsTheme.typography.titleSmallM,
                onTextLayout = { textLayoutResult -> onTextLayout(textLayoutResult) }
            )
        }
    )
}

private fun Modifier.tabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp,
): Modifier = composed {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "currentTabWidth",
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "indicatorOffset",
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SessionTopAppBarPreview() {
    KnightsTheme {
        SessionTopAppBar(
            sessionState = SessionState(
                sessions = persistentListOf(),
                listState = rememberLazyListState()
            ),
            onBackClick = { }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SessionTabIndicatorPreview() {
    KnightsTheme {
        SessionTabRow(
            selectedRoom = Room.TRACK2,
            rooms = Room.entries.toPersistentList(),
            onRoomSelect = { },
            modifier = Modifier.size(320.dp, 48.dp),
        )
    }
}
