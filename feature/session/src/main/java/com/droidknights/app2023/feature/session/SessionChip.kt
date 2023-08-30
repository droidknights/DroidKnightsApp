package com.droidknights.app2023.feature.session

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.droidknights.app2023.core.designsystem.component.TextChip
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.ui.textRes
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun TrackChip(room: Room) {
    TextChip(
        text = stringResource(id = room.textRes),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
internal fun TimeChip(dateTime: LocalDateTime) {
    val pattern = stringResource(id = R.string.session_time_fmt)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
    val time = remember { dateTime.toJavaLocalDateTime().toLocalTime() }

    TextChip(
        text = formatter.format(time),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )
}
