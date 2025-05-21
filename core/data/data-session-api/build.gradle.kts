plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.model.modelSession)

            api(libs.kotlinx.coroutines.core)
        }
    }
}

android.namespace = "com.droidknights.app.core.data.session.api"
