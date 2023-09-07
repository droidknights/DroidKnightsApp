package com.droidknights.app2023.feature.contributor.navigation

import androidx.navigation.NavController
import com.droidknights.app2023.feature.contributor.api.ContributorNavController
import javax.inject.Inject

internal class ContributorNavControllerImpl @Inject constructor() : ContributorNavController {
    override fun navigate(navController: NavController, navInfo: Unit) {
        navController.navigateContributor()
    }
}
