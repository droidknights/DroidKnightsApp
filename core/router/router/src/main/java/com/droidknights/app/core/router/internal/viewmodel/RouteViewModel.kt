package com.droidknights.app.core.router.internal.viewmodel

import androidx.lifecycle.ViewModel
import com.droidknights.app.core.router.internal.navigator.BackRoute
import com.droidknights.app.core.router.internal.navigator.InternalNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class RouteViewModel @Inject constructor(
    navigator: InternalNavigator,
) : ViewModel() {

    val sideEffect by lazy(LazyThreadSafetyMode.NONE) {
        navigator.channel.consumeAsFlow()
            .map { router ->
                when (router) {
                    is BackRoute -> RouteSideEffect.MoveNavigationBack
                    else -> RouteSideEffect.MoveNavigation(router)
                }
            }
    }
}
