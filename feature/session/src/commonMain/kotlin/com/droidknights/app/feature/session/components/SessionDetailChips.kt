package com.droidknights.app.feature.session.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.model.session.Session
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun SessionDetailChips(session: Session) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val sessionTags = session.tags.toPersistentList()

        TrackChip(room = session.room)
        TimeChip(dateTime = session.startTime)
        sessionTags.forEach { tag ->
            CategoryChip(text = tag.name)
        }
    }
}
