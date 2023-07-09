plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.main"
}

dependencies {
    implementation(project(":core:navigation"))
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.navigation)
}
