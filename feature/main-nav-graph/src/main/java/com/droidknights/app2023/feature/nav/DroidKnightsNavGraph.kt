package com.droidknights.app2023.feature.nav

import androidx.navigation.NavGraphBuilder

interface DroidKnightsNavGraph<T> {
    fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: T)
}
