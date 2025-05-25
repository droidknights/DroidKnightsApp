package com.droidknights.app.feature.session.component.chip

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.feature.session.R
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun TimeChip(dateTime: LocalDateTime) {
    val pattern = stringResource(id = R.string.session_time_fmt)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }
    val time = remember { dateTime.toJavaLocalDateTime().toLocalTime() }

    TextChip(
        text = formatter.format(time),
        containerColor = KnightsColor.Blue02A30,
        labelColor = MaterialTheme.colorScheme.surfaceContainerLow,
    )
}

@Preview
@Composable
private fun TimeChipPreview() {
    TimeChip(LocalDateTime(2022, 1, 1, 10, 22))
}
