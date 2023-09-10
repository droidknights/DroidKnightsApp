package com.droidknights.app2023.feature.contributor.api

import com.droidknights.app2023.feature.nav.DroidKnightsNavGraph

interface ContributorNavGraph : DroidKnightsNavGraph<ContributorNavGraphInfo>

data class ContributorNavGraphInfo(
    val onBackClick: () -> Unit,
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
)
