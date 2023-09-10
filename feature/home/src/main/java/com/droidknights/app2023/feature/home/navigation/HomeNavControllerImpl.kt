package com.droidknights.app2023.feature.home.navigation

import androidx.navigation.NavController
import com.droidknights.app2023.feature.home.api.HomeNavController
import com.droidknights.app2023.feature.home.api.HomeNavControllerInfo
import javax.inject.Inject

internal class HomeNavControllerImpl @Inject constructor() : HomeNavController {
    override fun route(): String = HomeRoute.route
    override fun isHomeRoute(currentRoute: String?): Boolean = currentRoute == HomeRoute.route

    override fun navigate(navController: NavController, navInfo: HomeNavControllerInfo) {
        navController.navigateHome(navInfo.navOptions)
    }
}
