package com.github.takahirom.roborazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.rules.TestWatcher
import org.junit.rules.RuleChain
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import com.github.takahirom.roborazzi.*
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder


@RunWith(ParameterizedRobolectricTestRunner::class)
@OptIn(InternalRoborazziApi::class, ExperimentalRoborazziApi::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RoborazziPreviewParameterizedTests(
    private val preview: ComposablePreview<Any>,
) {
    private val tester = getComposePreviewTester("com.droidknights.app.core.testing.DroidKnightKmpPreviewTester")
    private val testLifecycleOptions = tester.options().testLifecycleOptions
    @get:Rule
    val rule = RuleChain.outerRule(
      if(testLifecycleOptions is ComposePreviewTester.Options.JUnit4TestLifecycleOptions) {
        testLifecycleOptions.testRuleFactory()
      } else {
        object : TestWatcher() {}
      }
    )
    
    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [35], qualifiers = RobolectricDeviceQualifiers.Pixel5)
    @Test
    fun test() {
        tester.test(preview)
    }
    
    companion object {
        // lazy for performance
        val previews: List<ComposablePreview<Any>> by lazy {
            setupDefaultOptions()
            val tester = getComposePreviewTester("com.droidknights.app.core.testing.DroidKnightKmpPreviewTester")
            tester.previews()
        }
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun values(): List<ComposablePreview<Any>> = previews 
        
        fun setupDefaultOptions() {
            ComposePreviewTester.defaultOptionsFromPlugin = ComposePreviewTester.Options(
                scanOptions = ComposePreviewTester.Options.ScanOptions(
                  packages = listOf("com.droidknights.app.core.designsystem"),
                  includePrivatePreviews = true, 
                )
            )
        }
    } 
}