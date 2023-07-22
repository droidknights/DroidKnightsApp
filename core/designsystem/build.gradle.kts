plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.core.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
}
