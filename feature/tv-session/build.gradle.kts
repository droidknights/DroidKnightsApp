plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.tvsession"
}

dependencies {
    implementation(libs.kotlinx.immutable)

    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
}
