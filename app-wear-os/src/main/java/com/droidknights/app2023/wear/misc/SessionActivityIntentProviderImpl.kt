package com.droidknights.app2023.wear.misc

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import com.droidknights.app2023.core.playback.session.SessionActivityIntentProvider
import com.droidknights.app2023.feature.wearmain.WearMainActivity
import com.droidknights.app2023.feature.wearplayer.navigation.WearPlayerRoute
import javax.inject.Inject

class SessionActivityIntentProviderImpl @Inject constructor(
  private val context: Context,
) : SessionActivityIntentProvider {
  override fun toPlayer(): PendingIntent? {
    val deepLinkIntent = Intent(
      Intent.ACTION_VIEW,
      WearPlayerRoute.deepLinkUriPattern.toUri(),
      context,
      WearMainActivity::class.java
    )

    val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
      addNextIntentWithParentStack(deepLinkIntent)
      getPendingIntent(
        0,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    }
    return deepLinkPendingIntent
  }
}
