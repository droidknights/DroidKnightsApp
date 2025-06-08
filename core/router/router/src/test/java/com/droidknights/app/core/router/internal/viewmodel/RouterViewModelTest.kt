package com.droidknights.app.core.router.internal.viewmodel

import app.cash.turbine.test
import com.droidknights.app.core.router.internal.FakeRoute
import com.droidknights.app.core.router.internal.navigator.InternalNavigator
import com.droidknights.app.core.router.internal.navigator.InternalRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RouterViewModelTest {

    private val navigator = mock<InternalNavigator>()

    private val viewModel = RouterViewModel(navigator = navigator)

    @Test
    fun `Channel로 들어온 네비게이션 이벤트를 sideEffect로 방출한다`() = runTest {
        val mockChannel = Channel<InternalRoute>(Channel.BUFFERED)
        whenever(navigator.channel).thenReturn(mockChannel)

        viewModel.sideEffect.test {
            // move 테스트
            mockChannel.send(
                InternalRoute.Navigate(
                    route = FakeRoute,
                    saveState = true,
                    launchSingleTop = false,
                ),
            )
            Assertions.assertEquals(
                RouteSideEffect.Navigate(
                    route = FakeRoute,
                    saveState = true,
                    launchSingleTop = false,
                ),
                awaitItem(),
            )

            // Back 테스트
            mockChannel.send(InternalRoute.NavigateBack)
            Assertions.assertEquals(RouteSideEffect.NavigateBack, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
