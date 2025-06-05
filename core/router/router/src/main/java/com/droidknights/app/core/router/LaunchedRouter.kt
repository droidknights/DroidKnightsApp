package com.droidknights.app.core.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.droidknights.app.core.router.internal.viewmodel.RouteSideEffect
import com.droidknights.app.core.router.internal.viewmodel.RouterViewModel
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
    routerViewModel: RouterViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(routerViewModel, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            routerViewModel.sideEffect.collectLatest { sideEffect ->
                when (sideEffect) {
                    is RouteSideEffect.NavigateBack -> {
                        navHostController.popBackStack()
                    }

                    is RouteSideEffect.Navigate -> {
                        navHostController.navigate(sideEffect.route) {
                            if (sideEffect.saveState) {
                                navHostController.graph.findStartDestination().route?.let {
                                    popUpTo(it) {
                                        saveState = true
                                    }
                                }
                                restoreState = true
                            }
                            launchSingleTop = sideEffect.launchSingleTop
                        }
                    }
                }
            }
        }
    }
}
