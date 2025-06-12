package com.droidknights.app.primitive

import com.android.build.gradle.LibraryExtension
import com.droidknights.app.library
import com.droidknights.app.libs
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiPlatformRoborazziPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(libs.findPlugin("roborazzi").get().get().pluginId)
            }

            if (plugins.hasPlugin("com.android.library")) {
                extensions.configure<LibraryExtension> {
                    testOptions {
                        unitTests {
                            all { test ->
                                test.jvmArgs("-noverify")
                                test.systemProperties["robolectric.graphicsMode"] = "NATIVE"
                                test.systemProperties["robolectric.pixelCopyRenderMode"] =
                                    "hardware"

                                test.maxParallelForks = Runtime.getRuntime().availableProcessors()
                                test.testLogging {
                                    events.addAll(
                                        listOf(
                                            TestLogEvent.STARTED,
                                            TestLogEvent.PASSED,
                                            TestLogEvent.SKIPPED,
                                            TestLogEvent.FAILED
                                        )
                                    )
                                    showCauses = true
                                    showExceptions = true
                                    exceptionFormat = TestExceptionFormat.FULL
                                }
                            }
                            isIncludeAndroidResources = true
                        }
                    }

                    defaultConfig {
                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    }
                }
            }

            project.extensions.getByType<RoborazziExtension>().apply {
                val packageName = when {
                    path.startsWith(":feature:") -> {
                        "com.droidknights.app.feature.${path.removePrefix(":feature:")}"
                    }

                    path.startsWith(":core:") -> {
                        "com.droidknights.app.core.${path.removePrefix(":core:")}"
                    }

                    else -> "com.droidknights.app.$name"
                }

                @Suppress("unused", "OPT_IN_USAGE")
                generateComposePreviewRobolectricTests {
                    packages.set(listOf(packageName))
                    enable.set(true)
                    testerQualifiedClassName.set("com.droidknights.app.core.testing.DroidKnightKmpPreviewTester")
                    includePrivatePreviews.set(true)
                    outputDir.set(file("screenshot"))
                    robolectricConfig.set(
                        mapOf(
                            "sdk" to "[35]",
                            "qualifiers" to "RobolectricDeviceQualifiers.Pixel5"
                        )
                    )
                }
            }

            if (plugins.hasPlugin("com.android.library") && plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
                extensions.configure<KotlinMultiplatformExtension> {
                    sourceSets.androidUnitTest {
                        dependencies {
                            implementation(project(":core:testing"))
                            implementation(libs.library("junit"))
                            implementation(libs.library("robolectric"))
                            implementation(libs.library("roborazzi"))
                            implementation(libs.library("roborazzi-compose"))
                            implementation(libs.library("composable-preview-scanner"))
                            implementation(libs.library("composable-preview-scanner-jvm"))
                            implementation(libs.library("roborazzi-compose-preview-scanner-support"))
                            implementation(libs.library("coil"))
                            implementation(libs.library("coil-test"))
                        }
                    }
                }
            }
        }
    }
}