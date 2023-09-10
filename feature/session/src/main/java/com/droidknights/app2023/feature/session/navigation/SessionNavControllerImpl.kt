package com.droidknights.app2023.feature.session.navigation

import androidx.navigation.NavController
import com.droidknights.app2023.feature.session.api.SessionDetailNavController
import com.droidknights.app2023.feature.session.api.SessionDetailNavControllerInfo
import com.droidknights.app2023.feature.session.api.SessionNavController
import javax.inject.Inject

internal class SessionNavControllerImpl @Inject constructor() : SessionNavController {
    override fun navigate(navController: NavController, navInfo: Unit) {
        navController.navigateSession()
    }
}

internal class SessionDetailNavControllerImpl @Inject constructor() : SessionDetailNavController {

    override fun navigate(navController: NavController, navInfo: SessionDetailNavControllerInfo) {
        navController.navigateSessionDetail(navInfo.sessionId)
    }
}
