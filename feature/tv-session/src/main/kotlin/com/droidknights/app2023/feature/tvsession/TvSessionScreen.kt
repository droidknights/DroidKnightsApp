package com.droidknights.app2023.feature.tvsession

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyListScope
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.ui.RoomText
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun TvSessionScreen(
    onSessionClick: (Session) -> Unit,
    tvSessionViewModel: TvSessionViewModel = hiltViewModel(),
) {
    val tvSessionUiState by tvSessionViewModel.uiState.collectAsStateWithLifecycle()

    TvSessionContent(
        tvSessionUiState = tvSessionUiState,
        modifier = Modifier.fillMaxSize(),
        onSessionClick = onSessionClick,
    )
}

@Composable
private fun TvSessionContent(
    tvSessionUiState: TvSessionUiState,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (tvSessionUiState) {
        TvSessionUiState.Loading -> Box(modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is TvSessionUiState.Sessions -> TvLazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            sessionItems(
                items = tvSessionUiState.sessions,
                onSessionClick = onSessionClick,
            )
        }
    }
}

private fun TvLazyListScope.sessionItems(
    items: PersistentList<Session>,
    onSessionClick: (Session) -> Unit,
) {
    items.groupBy { it.room }.entries.forEach { (room, sessions) ->
        item {
            Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
                RoomTitle(
                    modifier = Modifier.fillMaxWidth(),
                    room = room
                )
                TvLazyRow(
                    modifier = Modifier
                        // https://github.com/android/nowinandroid/blob/main/feature/foryou/src/main/java/com/google/samples/apps/nowinandroid/feature/foryou/ForYouScreen.kt#L179-L191
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(
                                constraints.copy(
                                    maxWidth = constraints.maxWidth + 64.dp.roundToPx(),
                                ),
                            )
                            layout(placeable.width, placeable.height) {
                                placeable.place(0, 0)
                            }
                        }
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    items(sessions) { session ->
                        SessionCard(
                            modifier = Modifier.width(480.dp),
                            session = session,
                            onSessionClick = onSessionClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RoomTitle(
    modifier: Modifier = Modifier,
    room: Room,
) {
    Column(modifier = modifier) {
        RoomText(
            room = room,
            style = KnightsTheme.typography.titleLargeB,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}
