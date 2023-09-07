package com.droidknights.app2023.feature.home.api

import androidx.compose.foundation.layout.PaddingValues
import com.droidknights.app2023.feature.nav.DroidKnightsNavGraph

interface HomeNavGraph : DroidKnightsNavGraph<HomeNavGraphInfo>

data class HomeNavGraphInfo(
    val padding: PaddingValues,
    val onSessionClick: () -> Unit,
    val onContributorClick: () -> Unit,
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
)
