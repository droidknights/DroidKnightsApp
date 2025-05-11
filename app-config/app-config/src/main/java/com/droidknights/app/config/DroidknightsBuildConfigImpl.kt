package com.droidknights.app.config

import com.droidknights.app.app.config.BuildConfig
import com.droidknights.app.config.api.DroidknightsBuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DroidknightsBuildConfigImpl @Inject constructor() : DroidknightsBuildConfig {

    override fun isDebug(): Boolean =
        BuildConfig.DEBUG

    override fun userContentUrl(): String =
        "https://raw.githubusercontent.com/"

    override fun sponsorDataUrl(): String =
        "droidknights/DroidKnightsApp/main/core/data/src/main/assets/sponsors.json"
}
