package com.droidknights.app2023.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import com.droidknights.app2023.feature.home.api.HomeNavGraph
import com.droidknights.app2023.feature.home.api.HomeNavGraphInfo
import javax.inject.Inject

internal class HomeNavGraphImpl @Inject constructor() : HomeNavGraph {
    override fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: HomeNavGraphInfo) {
        val (padding, onSessionClick, onContributorClick, onShowErrorSnackBar) = navInfo

        navGraphBuilder.homeNavGraph(
            padding = padding,
            onSessionClick = onSessionClick,
            onContributorClick = onContributorClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}
