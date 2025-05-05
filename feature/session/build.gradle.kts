plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // TODO feature plugin
            implementation(libs.androidx.navigation.compose)
            implementation(projects.core.designsystem)
            implementation(projects.core.navigation)
        }
    }
}

android.namespace = "com.droidknights.app.feature.session"