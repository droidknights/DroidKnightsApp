plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // TODO feature plugin
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(projects.core.designsystem)
            implementation(projects.core.navigation)

            implementation(projects.core.data.dataSettingApi)
        }
    }
}

android.namespace = "com.droidknights.app.feature.setting"
