plugins {
    id("droidknights.kotlin.library")
    id("droidknights.kotlin.hilt")
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
