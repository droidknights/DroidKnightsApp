plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // ktor
            implementation(libs.ktor.client.content.negotiation )
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.logging)
            // koin
            implementation(libs.koin.core)
        }
    }
}
