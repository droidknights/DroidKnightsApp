package com.droidknights.app2023.feature.main

import androidx.compose.runtime.Immutable
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import javax.inject.Inject

@Immutable
class MainTabs @Inject constructor(
    private val tabs: Set<@JvmSuppressWildcards DroidKnightsTab>,
) {
    fun tabList() = tabs.sortedBy { it.order }
    fun startDestination(): String = tabs.first { it.isStartDestination }.route

    fun find(route: String): DroidKnightsTab? = tabs.firstOrNull { it.route == route }

    operator fun contains(route: String) = tabs.any { it.route == route }
}
