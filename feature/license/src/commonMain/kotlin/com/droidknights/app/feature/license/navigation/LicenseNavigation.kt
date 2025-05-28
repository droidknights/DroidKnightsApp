package com.droidknights.app.feature.license.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.Route
import com.droidknights.app.feature.license.LicenseScreen

fun NavController.navigateLicense() {
    navigate(Route.License)
}

fun NavGraphBuilder.licenseNavGraph(onBackClick: () -> Unit) {
    composable<Route.License> {
        LicenseScreen(onBackClick)
    }
}
