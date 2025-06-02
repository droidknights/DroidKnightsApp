plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.immutable)
        }
    }
}

android.namespace = "com.droidknights.app.feature.contributor"
