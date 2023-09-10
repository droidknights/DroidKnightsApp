plugins {
    id("droidknights.android.library-no-hilt")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.feature.setting.api"
}

dependencies {
    implementation(projects.feature.mainNavGraph)
    implementation(libs.androidx.compose.navigation)
}
