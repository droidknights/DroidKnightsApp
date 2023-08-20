package com.droidknights.app2023.feature.session

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.TextChip
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.ui.textRes
import kotlinx.datetime.toJavaLocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
internal fun SessionChips(session: Session) {
    Row {
        TrackChip(room = session.room)
        Spacer(modifier = Modifier.width(8.dp))
        TimeChip(session.startTime.toJavaLocalDateTime().toLocalTime())
    }
}

@Composable
private fun TrackChip(room: Room) {
    TextChip(
        text = stringResource(id = room.textRes),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
private fun TimeChip(time: LocalTime) {
    val pattern = stringResource(id = R.string.session_time_fmt)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
    TextChip(
        text = formatter.format(time),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )
}
