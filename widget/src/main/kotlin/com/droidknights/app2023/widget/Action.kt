package com.droidknights.app2023.widget

import android.content.Context
import android.content.Intent
import androidx.glance.action.Action
import androidx.glance.appwidget.action.actionStartActivity

fun actionStartActivityWithSessionId(context: Context, sessionId: String): Action =
    actionStartActivity(
        Intent(
            context.packageManager.getLaunchIntentForPackage(
                context.packageName
            )
        ).putExtra(DroidKnightsWidget.KEY_SESSION_ID, sessionId)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )

fun actionLaunchIntentForPackage(context: Context): Action = actionStartActivity(
    Intent(
        context.packageManager.getLaunchIntentForPackage(
            context.packageName
        )
    )
)