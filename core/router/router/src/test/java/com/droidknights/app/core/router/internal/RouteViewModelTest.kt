package com.droidknights.app.core.router.internal

import app.cash.turbine.test
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
        val mockChannel = Channel<RouteSideEffect>(Channel.BUFFERED)
        whenever(navigator.channel).thenReturn(mockChannel)

        viewModel.sideEffect.test {
            // move 테스트
            mockChannel.send(RouteSideEffect.MoveNavigation(MockRoute))
            Assertions.assertEquals(RouteSideEffect.MoveNavigation(MockRoute), awaitItem())

            // Back test
            mockChannel.send(RouteSideEffect.MoveNavigationBack)
            Assertions.assertEquals(RouteSideEffect.MoveNavigationBack, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
