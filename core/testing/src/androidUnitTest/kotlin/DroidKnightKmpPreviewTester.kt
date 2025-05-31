package com.droidknights.app.core.testing

import android.graphics.drawable.ColorDrawable
import androidx.test.core.app.ApplicationProvider
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.test.FakeImageLoaderEngine
import coil3.test.default
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester.Options
import com.github.takahirom.roborazzi.ComposePreviewTester.Options.JUnit4TestLifecycleOptions
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.captureRoboImage
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
            .getPreviews()
    }

    override fun test(preview: ComposablePreview<JvmAnnotationInfo>) {
        captureRoboImage("${preview.methodName}.png") {
            println(preview.methodName)
            ProvideAndroidContextToComposeResource()
            preview()
        }
    }
}

class CoilRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        val engine = FakeImageLoaderEngine.Builder()
            .default(ColorDrawable(android.graphics.Color.BLUE))
            .build()
        val imageLoader =
            ImageLoader.Builder(ApplicationProvider.getApplicationContext())
                .components { add(engine) }
                .build()
        SingletonImageLoader.setUnsafe(imageLoader)
    }
}


