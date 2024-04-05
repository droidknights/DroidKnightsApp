plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.wearsession"
}

dependencies {
    implementation(libs.kotlinx.immutable)

    implementation(libs.androidx.compose.wear.foundation)
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.compose.wear.navigation)
}
