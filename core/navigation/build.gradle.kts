import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    setNamespace("core.navigation")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
