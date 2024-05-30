package com.droidknights.app.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Contributor

@Serializable
data object Home

@Serializable
data object Setting

@Serializable
data object Bookmark

@Serializable
data object Session

@Serializable
data class SessionDetail(val sessionId: String)
