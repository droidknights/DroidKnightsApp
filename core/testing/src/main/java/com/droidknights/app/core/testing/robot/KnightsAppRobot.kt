package com.droidknights.app.core.testing.robot

import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.droidknights.app.core.testing.coroutines.runTestWithLogging
import com.droidknights.app.core.testing.rule.RobotTestRule
import com.github.takahirom.roborazzi.captureRoboImage
import kotlinx.coroutines.test.TestDispatcher
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class KnightsAppRobot @Inject constructor(
    private val testDispatcher: TestDispatcher,
) {

    @Inject
    lateinit var robotTestRule: RobotTestRule

    private lateinit var composeTestRule: AndroidComposeTestRule<*, *>
    operator fun invoke(
        block: KnightsAppRobot.() -> Unit,
    ) {
        runTestWithLogging(timeout = 30.seconds) {
            this@KnightsAppRobot.composeTestRule = robotTestRule.composeTestRule
            waitUntilIdle()
            block()
        }
    }

    fun capture() {
        composeTestRule
            .onNode(isRoot())
            .captureRoboImage()
    }

    fun waitUntilIdle() {
        composeTestRule.waitForIdle()
        testDispatcher.scheduler.advanceUntilIdle()
    }
}