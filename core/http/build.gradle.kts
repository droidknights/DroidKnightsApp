plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
}

android {
    namespace = "com.droidknights.app2023.core.http"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.inject)
}
