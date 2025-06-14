plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.core.router.routerApi)
        }
    }
}

android.namespace = "com.droidknights.app.feature.session.api"
