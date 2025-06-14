package com.droidknights.app.core.router.internal.viewmodel

import androidx.lifecycle.ViewModel
import com.droidknights.app.core.router.internal.navigator.InternalNavigator
import com.droidknights.app.core.router.internal.navigator.InternalRoute
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

internal class RouterViewModel(
    navigator: InternalNavigator,
) : ViewModel() {

    val sideEffect by lazy(LazyThreadSafetyMode.NONE) {
        navigator.channel.receiveAsFlow()
            .map { router ->
                when (router) {
                    is InternalRoute.Navigate -> RouteSideEffect.Navigate(
                        router.route,
                    )

                    is InternalRoute.NavigateBack -> RouteSideEffect.NavigateBack
                }
            }
    }
}
