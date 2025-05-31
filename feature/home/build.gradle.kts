@file:OptIn(ExperimentalComposeLibrary::class)

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("droidknights.feature")
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
            implementation(projects.core.ui.shader)
        }

        androidInstrumentedTest.dependencies {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.espresso.core)
            }
        }
    }
}

android.namespace = "com.droidknights.app.feature.home"
