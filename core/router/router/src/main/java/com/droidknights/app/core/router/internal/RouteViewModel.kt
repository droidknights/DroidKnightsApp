package com.droidknights.app.core.router.internal

import androidx.lifecycle.ViewModel
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
