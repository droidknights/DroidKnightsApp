package com.droidknights.app2023.feature.nav

interface DroidKnightsTab {
    val iconResId: Int
    val contentDescription: String
    val route: String
    val order: Int
    val isStartDestination: Boolean
}
