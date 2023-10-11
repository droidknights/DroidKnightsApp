package com.droidknights.app2023.core.playback.session

import android.app.PendingIntent

interface SessionActivityIntentProvider {
    fun toPlayer(): PendingIntent?
}