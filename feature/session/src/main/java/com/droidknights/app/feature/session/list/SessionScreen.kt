package com.droidknights.app.feature.session.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.ui.RoomText
import com.droidknights.app.feature.session.R
import com.droidknights.app.feature.session.list.component.SessionCard
import com.droidknights.app.feature.session.list.component.SessionListTopAppBar
import com.droidknights.app.feature.session.list.model.SessionState
import com.droidknights.app.feature.session.list.model.SessionUiState
import com.droidknights.app.feature.session.list.model.rememberSessionState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SessionScreen(
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    scrollToSessionId: String? = null,
    sessionListViewModel: SessionListViewModel = hiltViewModel(),
) {
    val density = LocalDensity.current
    val sessionUiState by sessionListViewModel.uiState.collectAsStateWithLifecycle()
    val sessionState = (sessionUiState as? SessionUiState.Sessions)?.sessions?.let { sessions ->
        rememberSessionState(sessions = sessions) // SessionUiState.Sessions
    } ?: rememberSessionState(sessions = persistentListOf()) // SessionUiState.Loading, SessionUiState.Error

    LaunchedEffect(Unit) {
        sessionListViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    var highlighted by remember { mutableStateOf(false) }

    LaunchedEffect(scrollToSessionId, sessionState.groups) {
        scrollToSessionId?.let { sessionId ->
            delay(300)
            val offset = with(density) { ((-6).dp).toPx().toInt() }
            sessionState.scrollToSession(sessionId, offset)
            delay(300)
            highlighted = true
            delay(500)
            highlighted = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SessionListTopAppBar(
            sessionState = sessionState,
            onBackClick = sessionListViewModel::navigateBack,
        )
        SessionList(
            sessionState = sessionState,
            onSessionClick = { session ->
                onSessionClick(session)
                sessionListViewModel.navigateSessionDetail(session.id)
            },
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 48.dp)
                .fillMaxSize(),
            highlightSessionId = if (highlighted) scrollToSessionId else null,
        )
    }
}

@Composable
private fun SessionList(
    sessionState: SessionState,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier,
    highlightSessionId: String? = null,
) {
    LazyColumn(
        modifier = modifier,
        state = sessionState.listState,
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sessionState.groups.forEachIndexed { groupIndex, group ->
            itemsIndexed(group.sessions) { sessionIndex, session ->
                val isFirstSession = sessionIndex == 0
                val isLastGroup = groupIndex == sessionState.groups.size - 1
                val isLastSession = sessionIndex == group.sessions.size - 1

                Column {
                    if (isFirstSession) {
                        RoomTitle(
                            room = group.room,
                            topPadding = if (groupIndex == 0) SessionTopSpace else SessionGroupSpace,
                        )
                    }
                    SessionCard(
                        session = session,
                        isHighlighted = session.id == highlightSessionId,
                        onSessionClick = onSessionClick,
                    )
                }

                if (isLastGroup && isLastSession) {
                    DroidKnightsFooter()
                }
            }
        }
    }
}

@Composable
private fun RoomTitle(
    room: Room,
    topPadding: Dp,
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = topPadding, end = 20.dp)
    ) {
        RoomText(
            room = room,
            style = KnightsTheme.typography.titleLargeB,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)

        Spacer(
            modifier = Modifier
                .height(32.dp)
        )
    }
}

@Composable
private fun DroidKnightsFooter() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 56.dp, bottom = 80.dp),
        text = stringResource(id = R.string.footer_text),
        style = KnightsTheme.typography.labelMediumR,
        color = Color.LightGray,
        textAlign = TextAlign.Center
    )
}

private val SessionTopSpace = 16.dp
private val SessionGroupSpace = 100.dp
