plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            api(libs.kotlinx.coroutines.core)
        }
    }
}

android.namespace = "com.droidknights.app.core.datastore.settings.api"
