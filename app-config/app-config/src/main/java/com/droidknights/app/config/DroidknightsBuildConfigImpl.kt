package com.droidknights.app.config

import com.droidknights.app.app.config.BuildConfig
import com.droidknights.app.config.api.DroidknightsBuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DroidknightsBuildConfigImpl @Inject constructor() : DroidknightsBuildConfig {

    override fun isDebug(): Boolean =
        BuildConfig.DEBUG

    override fun gitHubUrl(): String =
        "https://api.github.com/"

    override fun userDroidknightsUrl(): String =
        "https://raw.githubusercontent.com/"

    override fun sponsorDataUrl(): String =
        "droidknights/DroidKnightsApp/refs/heads/main/assets/sponsors/sponsors.json"

    override fun sessionsDataUrl(): String =
        "droidknights/DroidKnightsApp/refs/heads/main/assets/sessions/sessions.json"

    override fun contributorsDataUrl(): String =
        "droidknights/DroidKnightsApp/refs/heads/main/assets/contributors.json"
}
