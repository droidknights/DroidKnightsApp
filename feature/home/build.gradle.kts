@file:OptIn(ExperimentalComposeLibrary::class)

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
    id("io.github.takahirom.roborazzi")
}

roborazzi {
    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf("com.droidknights.app.feature.home")
        testerQualifiedClassName = "com.droidknights.app.feature.home.DroidKnightKmpPreviewTester"
    }
}

compose.resources {
    publicResClass = true
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // TODO feature plugin
            implementation(libs.androidx.navigation.compose)
            implementation(projects.core.designsystem)
            implementation(projects.core.navigation)
            implementation(projects.core.ui.shader)
        }

        androidUnitTest.dependencies {
            implementation(libs.junit)
            implementation(libs.robolectric)
            implementation(libs.androidx.compose.ui.test.junit4)
            implementation(libs.roborazzi)
            implementation(libs.roborazziCompose)
            implementation(libs.composablepreviewscanner)
            implementation(libs.composablePreviewScannerJvm)
            implementation(libs.roborazzi.compose.preview.scanner.support)
            implementation(libs.coil)
            implementation(libs.coil.test)
        }

        androidInstrumentedTest.dependencies {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.espresso.core)
            }
        }
    }
}

android {
    testOptions {
        unitTests {
            all { test ->
                test.jvmArgs("-noverify")
                test.systemProperties["robolectric.graphicsMode"] = "NATIVE"
                test.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"

                test.maxParallelForks = Runtime.getRuntime().availableProcessors()
                test.testLogging {
                    events.addAll(listOf(STARTED, PASSED, SKIPPED, FAILED))
                    showCauses = true
                    showExceptions = true
                    exceptionFormat = FULL
                }
            }
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

android.namespace = "com.droidknights.app.feature.home"
