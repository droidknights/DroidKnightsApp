plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.droidknights.app2023.core.sponsor.repo"
}

dependencies {
    implementation(projects.core.repo.sponsor.api)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
}
