package com.droidknights.app2023

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent

fun sendWidgetUpdateCommand(context: Context) {
    context.sendBroadcast(
        Intent(
            context,
            DroidKnightsWidgetReceiver::class.java
        ).setAction(
            AppWidgetManager.ACTION_APPWIDGET_UPDATE
        )
    )
}