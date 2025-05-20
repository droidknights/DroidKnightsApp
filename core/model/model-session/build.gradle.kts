plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
        }
    }
}

android.namespace = "com.droidknights.app.core.model.session"
