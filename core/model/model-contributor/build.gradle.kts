plugins {
    alias(libs.plugins.droidknights.kotlin.library)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

dependencies {
    api(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}
