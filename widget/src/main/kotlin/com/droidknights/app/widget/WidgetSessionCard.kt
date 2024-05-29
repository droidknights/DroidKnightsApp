package com.droidknights.app.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults
import com.droidknights.app.core.model.Session
import kotlinx.datetime.toJavaLocalDateTime

@Composable
internal fun WidgetSessionCard(uiState: WidgetSessionCardUiState) {
    val context = LocalContext.current

    Box(modifier = GlanceModifier.padding(bottom = 16.dp, end = 16.dp)) {
        Column(
            modifier = GlanceModifier.padding(16.dp).fillMaxWidth()
                .cornerRadius(12.dp).background(GlanceTheme.colors.tertiaryContainer).clickable(
                    actionStartActivityWithSessionId(context, uiState.session.id)
                )
        ) {
            Text(
                uiState.session.title,
                style = TextDefaults.defaultTextStyle.copy(
                    fontSize = 16.sp,
                    color = GlanceTheme.colors.onTertiaryContainer
                )
            )
            Row {
                Text(
                    uiState.session.toTimeString(),
                    style = TextDefaults.defaultTextStyle.copy(
                        fontSize = 14.sp,
                        color = GlanceTheme.colors.onTertiaryContainer
                    )
                )
                Spacer(modifier = GlanceModifier.width(4.dp))
                Text(
                    uiState.speakerLabel,
                    style = TextDefaults.defaultTextStyle.copy(
                        fontSize = 14.sp,
                        color = GlanceTheme.colors.onTertiaryContainer
                    )
                )
            }
        }
    }
}

private fun Session.toTimeString(): String =
    "${startTime.toJavaLocalDateTime().toLocalTime()}" +
    " ~ " +
    "${endTime.toJavaLocalDateTime().toLocalTime()}"