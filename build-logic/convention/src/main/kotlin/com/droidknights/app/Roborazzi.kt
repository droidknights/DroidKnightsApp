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

    dependencies {
        "testImplementation"(findLibrary("androidx.test.espresso.core"))
        "testImplementation"(findLibrary("junit4"))
        "testImplementation"(findLibrary("robolectric"))
        "testImplementation"(findLibrary("androidx.compose.ui.test"))
        "testImplementation"(findLibrary("hilt.android.testing"))
        "testImplementation"(findLibrary("roborazzi"))
        "testImplementation"(findLibrary("roborazziCompose"))
    }
}
