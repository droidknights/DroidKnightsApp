plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.core.data.dataContributorApi)
            implementation(projects.core.network)
        }
    }
}

android.namespace = "com.droidknights.app.core.data.contributor"
