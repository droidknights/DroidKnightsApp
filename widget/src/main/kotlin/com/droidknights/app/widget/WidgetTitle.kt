package com.droidknights.app.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults

@Composable
internal fun WidgetTitle() {
    val context = LocalContext.current

    Text(
        context.getString(R.string.widget_title),
        style = TextDefaults.defaultTextStyle.copy(
            fontSize = 24.sp,
            color = GlanceTheme.colors.onSurface
        ),
        modifier = GlanceModifier.clickable(
            actionLaunchIntentForPackage(context)
        )
    )
}
