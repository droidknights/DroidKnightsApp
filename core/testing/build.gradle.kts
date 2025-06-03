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
            implementation(libs.roborazzi)
            implementation(libs.roborazzi.compose)
            implementation(libs.composable.preview.scanner)
            implementation(libs.composable.preview.scanner.jvm)
            implementation(libs.roborazzi.compose.preview.scanner.support)
            implementation(libs.coil)
            implementation(libs.coil.test)
        }
    }
}

android.namespace = "com.droidknights.app.core.testing"
