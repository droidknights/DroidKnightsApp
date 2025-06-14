package com.droidknights.app.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.droidknights.app.feature.bookmark.navigation.bookmarkNavGraph
import com.droidknights.app.feature.contributor.navigation.contributorNavGraph
import com.droidknights.app.feature.home.navigation.homeNavGraph
import com.droidknights.app.feature.main.MainNavigator
import com.droidknights.app.feature.session.navigation.sessionNavGraph
import com.droidknights.app.feature.setting.navigation.settingNavGraph

@Composable
internal fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                onShowErrorSnackBar = onShowErrorSnackBar,
            )

            settingNavGraph(
                padding = padding,
            )

            bookmarkNavGraph(
                onShowErrorSnackBar = onShowErrorSnackBar,
            )

            contributorNavGraph(
                onShowErrorSnackBar = onShowErrorSnackBar,
            )

            sessionNavGraph(
                onShowErrorSnackBar = onShowErrorSnackBar,
            )
        }
    }
}
