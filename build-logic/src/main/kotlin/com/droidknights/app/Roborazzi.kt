package com.droidknights.app

import com.android.build.gradle.TestedExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureRoborazzi() {
    with(pluginManager) {
        apply("io.github.takahirom.roborazzi")
        apply("com.google.devtools.ksp")
    }
    extensions.configure<TestedExtension> {
        testOptions {
            unitTests {
                all {
                    it.maxParallelForks = Runtime.getRuntime().availableProcessors()
                    // -Pscreenshot to filter screenshot tests
                    it.useJUnit {
                        if (project.hasProperty("screenshot")) {
                            project.logger.lifecycle("Screenshot tests are included")
                            includeCategories("com.droidknights.app.core.testing.category.ScreenshotTests")
                        }
                    }
                }
            }
        }
    }
    val libs = extensions.libs
    dependencies {
        "testImplementation"(libs.findLibrary("androidx.test.espresso.core").get())
        "testImplementation"(libs.findLibrary("junit4").get())
        "testImplementation"(libs.findLibrary("robolectric").get())
        "testImplementation"(libs.findLibrary("androidx.compose.ui.test").get())
        "testImplementation"(libs.findLibrary("hilt.android.testing").get())
        "testImplementation"(libs.findLibrary("roborazzi").get())
        "testImplementation"(libs.findLibrary("roborazziCompose").get())
    }
}
