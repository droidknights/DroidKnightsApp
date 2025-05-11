package com.droidknights.app.config.api

interface DroidknightsBuildConfig {

    fun isDebug(): Boolean = false

    fun userContentUrl(): String = ""

    fun sponsorDataUrl(): String = ""
}
