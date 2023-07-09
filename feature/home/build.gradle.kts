plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.home"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.androidx.compose.navigation)
}
