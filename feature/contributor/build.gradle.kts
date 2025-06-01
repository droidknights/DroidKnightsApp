plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.immutable)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.core.designsystem)
            implementation(projects.core.navigation)
        }
    }
}

android.namespace = "com.droidknights.app.feature.contributor"
