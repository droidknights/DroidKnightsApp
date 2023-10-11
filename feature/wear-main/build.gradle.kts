plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.wearmain"
}

dependencies {
    implementation(projects.feature.wearSession)
    implementation(projects.feature.wearPlayer)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.androidx.compose.wear.foundation)
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.compose.wear.navigation)
}
