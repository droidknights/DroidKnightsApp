plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.bookmark)
            implementation(projects.feature.contributor)
            implementation(projects.feature.home)
            implementation(projects.feature.session)
            implementation(projects.feature.setting)

            // TODO feature plugin
            implementation(projects.core.designsystem)
            implementation(libs.androidx.navigation.compose)
            implementation(projects.core.navigation)
        }
    }
}

android.namespace = "com.droidknights.app.feature.main"
