package com.droidknights.app.core.router.internal.navigator

import app.cash.turbine.test
import com.droidknights.app.core.router.internal.FakeRoute
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NavigatorImplTest {

    private val navigator = NavigatorImpl()

    @Test
    fun `test navigate`() = runTest {
        navigator.channel.consumeAsFlow().test {
            // fake route test
            navigator.navigate(FakeRoute)
            Assertions.assertEquals(InternalRoute.Navigate(FakeRoute, false), awaitItem())

            // Back test
            navigator.navigateBack()
            Assertions.assertEquals(InternalRoute.NavigateBack, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
