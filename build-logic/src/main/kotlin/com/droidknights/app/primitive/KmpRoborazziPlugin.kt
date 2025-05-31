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

class KmpRoborazziPlugin : Plugin<Project> {
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

            // Configure Roborazzi extension
            project.extensions.getByType<RoborazziExtension>().apply {
                val packageName = "com.droidknights.app.feature.$name"

                @Suppress("unused", "OPT_IN_USAGE")
                generateComposePreviewRobolectricTests {
                    packages.set(listOf(packageName))
                    enable.set(true)
                    testerQualifiedClassName.set("testing.DroidKnightKmpPreviewTester")
                }
            }

            // Add dependencies for Roborazzi testing
            if (plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
                extensions.configure<KotlinMultiplatformExtension> {
                    sourceSets.apply {
                        getByName("androidUnitTest") {
                            dependencies {
                                implementation(libs.library("junit"))
                                implementation(libs.library("robolectric"))
                                implementation(libs.library("androidx-compose-ui-test-junit4"))
                                implementation(libs.library("roborazzi"))
                                implementation(libs.library("roborazziCompose"))
                                implementation(libs.library("composablepreviewscanner"))
                                implementation(libs.library("composablePreviewScannerJvm"))
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
}