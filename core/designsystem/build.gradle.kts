plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.core.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
    
    implementation(libs.landscapist.bom)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)
}
