plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            api(libs.kotlinx.datetime)
        }
    }
}

android.namespace = "com.droidknights.app.shared"
