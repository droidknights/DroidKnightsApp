package com.droidknights.app2023.feature.home.navigation

import com.droidknights.app2023.feature.home.R
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import javax.inject.Inject

internal class HomeTab @Inject constructor() : DroidKnightsTab {
    override val iconResId: Int = R.drawable.ic_home
    override val contentDescription: String = "홈"
    override val route: String = HomeRoute.route
    override val order: Int = 1
    override val isStartDestination: Boolean = true
}
