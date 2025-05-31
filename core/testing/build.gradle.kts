plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
        androidMain.dependencies {
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
    }
}

android.namespace = "com.droidknights.app.core.testing"
