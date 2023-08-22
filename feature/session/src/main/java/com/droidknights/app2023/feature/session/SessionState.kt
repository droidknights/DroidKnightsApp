package com.droidknights.app2023.feature.session

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

data class SessionGroup(
    val room: Room,
    val sessions: PersistentList<Session>,
)

@Stable
class SessionState(
    private val sessions: PersistentList<Session>,
    val listState: LazyListState,
    selectedRoom: Room? = sessions.map { it.room }.firstOrNull(),
) {
    val groups: List<SessionGroup> = sessions
        .groupBy { it.room }
        .map { (room, sessions) -> SessionGroup(room, sessions.toPersistentList()) }

    val rooms: List<Room> = sessions.map { it.room }.distinct()

    private val roomPositions: Map<Room, Int> = buildMap {
        var position = 0
        groups.forEach { group ->
            put(group.room, position)
            position += group.sessions.size
        }
    }

    var selectedRoom: Room? by mutableStateOf(selectedRoom)
        private set

    val isAtTop by derivedStateOf {
        listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
    }

    fun groupIndex(index: Int): Room? {
        for ((room, position) in roomPositions) {
            if (position == index) {
                return room
            }
        }
        return null
    }

    fun select(room: Room) {
        selectedRoom = room
    }

    suspend fun scrollTo(room: Room) {
        val index = roomPositions[room] ?: return
        listState.animateScrollToItem(index)
    }

    companion object {
        fun Saver(
            sessions: PersistentList<Session>,
            listState: LazyListState,
        ): Saver<SessionState, *> = Saver(
            save = { it.selectedRoom },
            restore = { selectedRoom ->
                SessionState(
                    sessions = sessions,
                    listState = listState,
                    selectedRoom = selectedRoom,
                )
            }
        )
    }
}

@Composable
internal fun rememberSessionState(
    sessions: PersistentList<Session>,
    listState: LazyListState = rememberLazyListState(),
): SessionState {
    val state = rememberSaveable(
        sessions,
        listState,
        saver = SessionState.Saver(sessions, listState),
    ) {
        SessionState(sessions, listState)
    }
    LaunchedEffect(sessions, listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .mapNotNull { index -> state.groupIndex(index) }
            .distinctUntilChanged()
            .collect { room -> state.select(room) }
    }
    return state
}
