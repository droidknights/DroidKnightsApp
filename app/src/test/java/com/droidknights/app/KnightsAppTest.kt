package com.droidknights.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droidknights.app.core.testing.category.ScreenshotTests
import com.droidknights.app.core.testing.robot.KnightsAppRobot
import com.droidknights.app.core.testing.rule.RobotTestRule
import com.droidknights.app.feature.main.MainActivity
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
@Category(ScreenshotTests::class)
class KnightsAppTest {

    @get:Rule
    @BindValue
    val robotTestRule: RobotTestRule = RobotTestRule<MainActivity>(this)

    @Inject
    lateinit var knightsAppRobot: KnightsAppRobot

    @Test
    fun checkStartupShot() {
        knightsAppRobot {
            capture()
        }
    }

    @Test
    @Config(qualifiers = RobolectricDeviceQualifiers.MediumTablet)
    fun checkMediumTabletLaunchShot() {
        knightsAppRobot {
            capture()
        }
    }
}