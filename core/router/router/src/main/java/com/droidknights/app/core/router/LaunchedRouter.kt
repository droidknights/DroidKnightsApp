package com.droidknights.app.core.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.droidknights.app.core.router.internal.viewmodel.RouteSideEffect
import com.droidknights.app.core.router.internal.viewmodel.RouteViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchedRouter(
    navHostController: NavHostController,
) {
    InternalLaunchedRouter(
        navHostController = navHostController,
    )
}

@Composable
private fun InternalLaunchedRouter(
    navHostController: NavHostController,
    routeViewModel: RouteViewModel = hiltViewModel(),
) {
    LaunchedEffect(routeViewModel) {
        routeViewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is RouteSideEffect.MoveNavigationBack -> {
                    navHostController.popBackStack()
                }

                is RouteSideEffect.MoveNavigation -> {
                    navHostController.navigate(sideEffect.route) {
                        navHostController.graph.findStartDestination().route?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        restoreState = true
                    }
                }
            }
        }
    }
}
