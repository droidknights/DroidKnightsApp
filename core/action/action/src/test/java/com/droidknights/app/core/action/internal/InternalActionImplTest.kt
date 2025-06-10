package com.droidknights.app.core.action.internal

import app.cash.turbine.test
import com.droidknights.app.core.action.api.Action
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class InternalActionImplTest {

    private val flowAction = InternalActionImpl()

    @Test
    fun `test flowAction`() = runTest {
        flowAction.flowAction()
            .test {
                expectNoEvents()

                flowAction.nextAction(SomeAction)
                Assertions.assertEquals(SomeAction, awaitItem())

                flowAction.nextAction(NextAction)
                Assertions.assertEquals(NextAction, awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
    }

    companion object {

        private val SomeAction = object : Action {}

        private val NextAction = object : Action {}
    }
}
