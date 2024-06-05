package com.droidknights.app.feature.session.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.Room
import com.droidknights.app.core.model.Session
import com.droidknights.app.core.ui.RoomText

@Composable
internal fun SessionItem(
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

        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)

        Spacer(modifier = Modifier.height(32.dp))
    }
}
