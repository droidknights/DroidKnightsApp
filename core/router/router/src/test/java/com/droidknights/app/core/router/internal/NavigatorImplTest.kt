package com.droidknights.app.core.router.internal

import app.cash.turbine.test
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NavigatorImplTest {

    private val navigator = NavigatorImpl()

    @Test
    fun `test move`() = runTest {
        navigator.channel.consumeAsFlow().test {
            // move 테스트
            navigator.navigate(MockRoute)
            Assertions.assertEquals(MockRoute, awaitItem())

            // Back test
            navigator.back()
            Assertions.assertEquals(BackRoute, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
