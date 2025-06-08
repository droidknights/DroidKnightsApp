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
    fun `navigate와 navigateBack 호출 시 각각의 라우트 이벤트가 channel로 전달된다`() = runTest {
        navigator.channel.consumeAsFlow().test {
            // fake route 테스트
            navigator.navigate(FakeRoute)
            Assertions.assertEquals(
                InternalRoute.Navigate(
                    route = FakeRoute,
                    saveState = false,
                    launchSingleTop = false,
                ),
                awaitItem(),
            )

            // Back 테스트
            navigator.navigateBack()
            Assertions.assertEquals(InternalRoute.NavigateBack, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
