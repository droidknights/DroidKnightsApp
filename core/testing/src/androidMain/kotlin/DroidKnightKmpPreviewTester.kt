package com.droidknights.app.core.testing

import android.graphics.Color.BLUE
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.core.graphics.drawable.toDrawable
import androidx.test.core.app.ApplicationProvider
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.test.FakeImageLoaderEngine
import coil3.test.default
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester.Options
import com.github.takahirom.roborazzi.ComposePreviewTester.Options.JUnit4TestLifecycleOptions
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.captureRoboImage
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.jvm.JvmAnnotationInfo
import sergio.sastre.composable.preview.scanner.jvm.JvmAnnotationScanner

@OptIn(ExperimentalRoborazziApi::class)
class DroidKnightKmpPreviewTester : ComposePreviewTester<JvmAnnotationInfo> {
    override fun options(): Options {
        return super.options().copy(
            testLifecycleOptions = JUnit4TestLifecycleOptions {
                CoilRule()
            },
        )
    }

    override fun previews(): List<ComposablePreview<JvmAnnotationInfo>> {
        return JvmAnnotationScanner("org.jetbrains.compose.ui.tooling.preview.Preview")
            .scanPackageTrees(*options().scanOptions.packages.toTypedArray())
            .includePrivatePreviews()
            .getPreviews()
    }

    override fun test(preview: ComposablePreview<JvmAnnotationInfo>) {
        val fileName = "${preview.methodName}.png"
        val filePath = "screenshot/$fileName"

        captureRoboImage(filePath) {
            println("Capturing preview: ${preview.methodName}")
            ProvideAndroidContextToComposeResource()
            preview()
        }
    }
}

class CoilRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        val engine = FakeImageLoaderEngine.Builder()
            .default(BLUE.toDrawable())
            .build()
        val imageLoader =
            ImageLoader.Builder(ApplicationProvider.getApplicationContext())
                .components { add(engine) }
                .build()
        @OptIn(DelicateCoilApi::class)
        SingletonImageLoader.setUnsafe(imageLoader)
    }
}

@Composable
private fun ProvideAndroidContextToComposeResource(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalInspectionMode provides true) {
        PreviewContextConfigurationEffect()
        content()
    }
}
