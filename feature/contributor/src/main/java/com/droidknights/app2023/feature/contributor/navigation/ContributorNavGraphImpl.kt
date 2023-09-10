package com.droidknights.app2023.feature.contributor.navigation

import androidx.navigation.NavGraphBuilder
import com.droidknights.app2023.feature.contributor.api.ContributorNavGraph
import com.droidknights.app2023.feature.contributor.api.ContributorNavGraphInfo
import javax.inject.Inject

internal class ContributorNavGraphImpl @Inject constructor() : ContributorNavGraph {
    override fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: ContributorNavGraphInfo) {
        navGraphBuilder.contributorNavGraph(
            navInfo.onBackClick,
            navInfo.onShowErrorSnackBar
        )
    }
}
