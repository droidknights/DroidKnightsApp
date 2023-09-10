plugins {
    id("droidknights.android.library-no-hilt")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.feature.nav"
}

dependencies {
    implementation(libs.androidx.compose.navigation)
}
