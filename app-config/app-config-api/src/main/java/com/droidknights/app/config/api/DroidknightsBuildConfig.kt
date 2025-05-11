package com.droidknights.app.config.api

interface DroidknightsBuildConfig {

    fun isDebug(): Boolean = false

    fun gitHubUrl(): String = ""

    fun userDroidknightsUrl(): String = ""

    fun sponsorDataUrl(): String = ""

    fun sessionsDataUrl(): String = ""

    fun contributorsDataUrl(): String = ""
}
