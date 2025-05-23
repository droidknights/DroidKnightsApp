package com.droidknights.app.feature.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.Divider
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.feature.session.components.SessionCard
import com.droidknights.app.feature.session.components.SessionTopAppBar
import com.droidknights.app.feature.session.model.SessionState
import com.droidknights.app.feature.session.model.SessionUiState
import com.droidknights.app.feature.session.model.rememberSessionState
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.footer_text
import droidknights.feature.session.generated.resources.session_room_etc
import droidknights.feature.session.generated.resources.session_room_track_01
import droidknights.feature.session.generated.resources.session_room_track_02
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SessionScreen(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    sessionViewModel: SessionViewModel = koinViewModel(),
) {
    val sessionUiState by sessionViewModel.uiState.collectAsStateWithLifecycle()
    val sessionState = (sessionUiState as? SessionUiState.Sessions)?.sessions?.let { sessions ->
        rememberSessionState(sessions = sessions) // SessionUiState.Sessions
    } ?: rememberSessionState(sessions = persistentListOf()) // SessionUiState.Loading, SessionUiState.Error

    Surface(
        color = KnightsTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize(),
        ) {
            SessionTopAppBar(
                onBackClick = onBackClick,
            )
            SessionList(
                sessionState = sessionState,
                modifier = Modifier.weight(1F),
                onSessionClick = onSessionClick,
            )
        }
    }
}

@Composable
private fun SessionList(
    sessionState: SessionState,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = sessionState.listState,
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sessionState.groups.forEachIndexed { groupIndex, group ->
            item {
                RoomTitle(
                    room = group.room,
                    topPadding = if (groupIndex == 0) SessionTopSpace else SessionGroupSpace,
                )
            }
            items(group.sessions) { session ->
                SessionCard(session = session, onSessionClick = onSessionClick)
            }
        }
        item {
            DroidKnightsFooter()
        }
    }
}

@Composable
private fun RoomTitle(
    room: Room,
    topPadding: Dp,
) {
    Column(
        modifier = Modifier.padding(
            start = 24.dp,
            top = topPadding,
            end = 24.dp,
            bottom = 16.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = when (room) {
                Room.TRACK1 -> stringResource(resource = Res.string.session_room_track_01)
                Room.TRACK2 -> stringResource(resource = Res.string.session_room_track_02)
                Room.ETC -> stringResource(resource = Res.string.session_room_etc)
            },
            style = KnightsTheme.typography.titleLargeB,
        )
        Divider(thickness = 2.dp)
    }
}

private val SessionTopSpace = 16.dp
private val SessionGroupSpace = 84.dp

@Composable
private fun DroidKnightsFooter() {
    Text(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 56.dp, bottom = 80.dp),
        text = stringResource(resource = Res.string.footer_text),
        style = KnightsTheme.typography.labelMediumR,
        color = Color.LightGray,
        textAlign = TextAlign.Center,
    )
}
