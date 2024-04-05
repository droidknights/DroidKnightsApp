plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.tvsession"
}

dependencies {
    implementation(libs.kotlinx.immutable)

    implementation(libs.androidx.compose.tv.foundation)
    implementation(libs.androidx.compose.tv.material)
}
