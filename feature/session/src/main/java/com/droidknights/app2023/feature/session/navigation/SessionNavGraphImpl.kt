package com.droidknights.app2023.feature.session.navigation

import androidx.navigation.NavGraphBuilder
import com.droidknights.app2023.feature.session.api.SessionNavGraph
import com.droidknights.app2023.feature.session.api.SessionNavGraphInfo
import javax.inject.Inject

internal class SessionNavGraphImpl @Inject constructor() : SessionNavGraph {
    override fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: SessionNavGraphInfo) {
        navGraphBuilder.sessionNavGraph(
            navInfo.onBackClick,
            navInfo.onSessionClick,
            navInfo.onShowErrorSnackBar
        )
    }
}
