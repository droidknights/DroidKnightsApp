plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(projects.core.data.dataSettingApi)
        }
    }
}

android.namespace = "com.droidknights.app.core.data.setting"
