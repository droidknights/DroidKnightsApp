package com.droidknights.app.feature.session.api

import com.droidknights.app.core.router.api.model.Route
import kotlinx.serialization.Serializable

@Serializable
data class RouteSession(val sessionId: String? = null) : Route

@Serializable
data class RouteSessionDetail(val sessionId: String) : Route
