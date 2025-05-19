plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
        }
    }
}

android.namespace = "com.droidknights.app.core.data.setting.api"
