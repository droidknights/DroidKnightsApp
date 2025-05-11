package com.droidknights.app.core.data.contributor.api.fake

import com.droidknights.app.config.api.DroidknightsBuildConfig

internal class FakeDroidknightsBuildConfig : DroidknightsBuildConfig {

    override fun sessionsDataUrl(): String =
        "https://droidknights.dev"
}
