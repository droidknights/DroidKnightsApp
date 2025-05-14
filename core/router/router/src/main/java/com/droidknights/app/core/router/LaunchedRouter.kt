package com.droidknights.app.core.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.droidknights.app.core.router.internal.RouteSideEffect
import com.droidknights.app.core.router.internal.RouteViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchedRouter(
    navHostController: NavHostController,
) {
    InternalLaunchedCaRouter(
        navHostController = navHostController,
    )
}

@Composable
private fun InternalLaunchedCaRouter(
    navHostController: NavHostController,
    routeViewModel: RouteViewModel = hiltViewModel(),
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(routeViewModel) {

        lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
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
}
