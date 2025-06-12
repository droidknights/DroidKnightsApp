package com.droidknights.app.core.router.internal.viewmodel

import androidx.lifecycle.ViewModel
import com.droidknights.app.core.router.internal.navigator.InternalNavigator
import com.droidknights.app.core.router.internal.navigator.InternalRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class RouterViewModel @Inject constructor(
    navigator: InternalNavigator,
) : ViewModel() {

    val sideEffect by lazy(LazyThreadSafetyMode.NONE) {
        navigator.channel.receiveAsFlow()
            .map { router ->
                when (router) {
                    is InternalRoute.Navigate -> RouteSideEffect.Navigate(
                        router.route,
                        router.saveState,
                        router.launchSingleTop,
                    )

                    is InternalRoute.NavigateWeb -> RouteSideEffect.NavigateWeb(router.url)

                    is InternalRoute.NavigateBack -> RouteSideEffect.NavigateBack
                }
            }
    }
}
