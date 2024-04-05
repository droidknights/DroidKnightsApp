package com.droidknights.app2023.feature.wearsession

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.ui.RoomText
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.launch

@Composable
internal fun WearSessionScreen(
    onSessionClick: (Session) -> Unit,
    wearSessionViewModel: WearSessionViewModel = hiltViewModel(),
) {
    val wearSessionUiState by wearSessionViewModel.uiState.collectAsStateWithLifecycle()

    WearSessionContent(
        wearSessionUiState = wearSessionUiState,
        modifier = Modifier.fillMaxSize(),
        onSessionClick = onSessionClick,
    )
}

@Composable
private fun WearSessionContent(
    wearSessionUiState: WearSessionUiState,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (wearSessionUiState) {
        WearSessionUiState.Loading -> Box(modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is WearSessionUiState.Sessions -> {
            val focusRequester = remember { FocusRequester() }
            val coroutineScope = rememberCoroutineScope()
            val listState = rememberScalingLazyListState()
            // https://developer.android.com/training/wearables/compose/lists
            ScalingLazyColumn(
                modifier = modifier
                    // https://developer.android.com/training/wearables/compose/rotary-input#scroll
                    .onRotaryScrollEvent { event ->
                        coroutineScope.launch {
                            listState.scrollBy(event.verticalScrollPixels)
                        }
                        true
                    }
                    .focusRequester(focusRequester)
                    .focusable(),
                state = listState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                sessionItems(
                    items = wearSessionUiState.sessions,
                    onSessionClick = onSessionClick,
                )
            }
            LaunchedEffect(Unit) { focusRequester.requestFocus() }
        }
    }
}

private fun ScalingLazyListScope.sessionItems(
    items: PersistentList<Session>,
    onSessionClick: (Session) -> Unit,
) {
    items.groupBy { it.room }.entries.forEach { (room, sessions) ->
        item("room-title-${room.name}") {
            RoomTitle(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                room = room
            )
        }
        items(sessions, key = { session -> "session-item-$session" }) { session ->
            SessionCard(
                modifier = Modifier.fillMaxWidth(),
                session = session,
                onSessionClick = onSessionClick
            )
        }
    }
}

@Composable
private fun RoomTitle(
    modifier: Modifier = Modifier,
    room: Room,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoomText(
            room = room,
            style = KnightsTheme.typography.titleLargeB,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}
