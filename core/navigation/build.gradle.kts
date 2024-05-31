import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    setNamespace("core.navigation")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
