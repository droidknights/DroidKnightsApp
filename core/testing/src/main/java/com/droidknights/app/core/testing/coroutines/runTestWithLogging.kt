package com.droidknights.app.core.testing.coroutines

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun runTestWithLogging(
    context: CoroutineContext = EmptyCoroutineContext,
    timeout: Duration = 30.seconds,
    testBody: suspend TestScope.() -> Unit,
) = runTest(context, timeout) {
    runCatching {
        testBody()
    }.onFailure { exception ->
        exception.printStackTrace()
        throw exception
    }
}
