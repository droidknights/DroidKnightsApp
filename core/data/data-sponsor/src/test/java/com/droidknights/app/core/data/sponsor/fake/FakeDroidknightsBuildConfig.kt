package com.droidknights.app.core.data.sponsor.fake

import com.droidknights.app.config.api.DroidknightsBuildConfig

internal class FakeDroidknightsBuildConfig : DroidknightsBuildConfig {

    override fun sponsorDataUrl(): String =
        "https://droidknights.dev"
}
