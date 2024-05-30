package com.droidknights.app.core.navigation

import kotlinx.serialization.Serializable

interface Route

interface MainTabRoute : Route

@Serializable
data object Contributor : Route

@Serializable
data object Home : MainTabRoute

@Serializable
data object Setting : MainTabRoute

@Serializable
data object Bookmark : MainTabRoute

@Serializable
data object Session : Route

@Serializable
data class SessionDetail(val sessionId: String) : Route
