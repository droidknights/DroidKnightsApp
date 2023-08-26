package com.droidknights.app2023.feature.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.ui.RoomText
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SessionScreen(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    sessionViewModel: SessionViewModel = hiltViewModel(),
) {
    val sessionUiState by sessionViewModel.uiState.collectAsStateWithLifecycle()
    val sessionState = (sessionUiState as? SessionUiState.Sessions)?.sessions?.let { sessions ->
            rememberSessionState(sessions = sessions) // SessionUiState.Sessions
        } ?: rememberSessionState(sessions = persistentListOf()) // SessionUiState.Loading, SessionUiState.Error

    LaunchedEffect(true) {
        sessionViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SessionTopAppBar(
            sessionState = sessionState,
            onBackClick = onBackClick,
        )
        SessionContent(
            sessionState = sessionState,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 48.dp)
                .fillMaxSize(),
            onSessionClick = onSessionClick,
        )
    }
}

@Composable
private fun SessionContent(
    sessionState: SessionState,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = sessionState.listState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sessionState.groups.forEachIndexed { index, group ->
            val topPadding = if (index == 0) {
                SessionTopSpace
            } else {
                SessionGroupSpace
            }
            sessionItems(
                room = group.room,
                items = group.sessions,
                topPadding = topPadding,
                onItemClick = onSessionClick,
            )
        }
    }
}

private val SessionTopSpace = 4.dp
private val SessionGroupSpace = 16.dp

private fun LazyListScope.sessionItems(
    room: Room,
    items: PersistentList<Session>,
    topPadding: Dp,
    onItemClick: (Session) -> Unit,
) {
    itemsIndexed(items) { index, item ->
        SessionItem(
            index = index,
            item = item,
            room = room,
            topPadding = topPadding,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun SessionItem(
    index: Int,
    item: Session,
    room: Room,
    topPadding: Dp,
    onItemClick: (Session) -> Unit,
) {
    Column {
        if (index == 0) {
            RoomTitle(room = room, topPadding = topPadding)
        }
        SessionCard(session = item, onSessionClick = onItemClick)
    }
}

@Composable
private fun RoomTitle(
    room: Room,
    topPadding: Dp,
) {
    Column(modifier = Modifier.padding(start = 20.dp, top = topPadding, end = 20.dp)) {
        RoomText(
            room = room,
            style = KnightsTheme.typography.titleLargeB,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
        Spacer(modifier = Modifier.height(32.dp))
    }
}
