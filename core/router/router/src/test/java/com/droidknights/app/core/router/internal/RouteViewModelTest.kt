package com.droidknights.app.core.router.internal

import app.cash.turbine.test
import com.droidknights.app.core.router.api.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RouteViewModelTest {

    private val navigator = mock<InternalNavigator>()

    private val viewModel = RouteViewModel(navigator = navigator)

    @Test
    fun `test sideEffect`() = runTest {
        val mockChannel = Channel<Route>(Channel.BUFFERED)
        whenever(navigator.channel).thenReturn(mockChannel)

        viewModel.sideEffect.test {
            // move 테스트
            mockChannel.send(MockRoute)
            Assertions.assertEquals(RouteSideEffect.MoveNavigation(MockRoute), awaitItem())

            // Back test
            mockChannel.send(RouteBack)
            Assertions.assertEquals(RouteSideEffect.MoveNavigationBack, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
