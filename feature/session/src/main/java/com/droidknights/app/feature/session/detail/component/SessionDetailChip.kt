package com.droidknights.app.feature.session.detail.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.OutlineChip
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.ui.textRes
import com.droidknights.app.feature.session.component.SessionPreviewParameterProvider
import com.droidknights.app.feature.session.component.chip.TimeChip
import com.droidknights.app.feature.session.component.chip.TrackChip
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun SessionDetailChips(session: Session) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val sessionTags = session.tags.toPersistentList()

        TrackChip(stringRes = session.room.textRes)
        TimeChip(dateTime = session.startTime)
        sessionTags.forEach { tag ->
            OutlineChip(text = tag.name)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSessionDetailChips(@PreviewParameter(SessionPreviewParameterProvider::class) session: Session) {
    KnightsTheme {
        SessionDetailChips(
            session = session,
        )
    }
}
