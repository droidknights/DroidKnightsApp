plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.droidknights.app2023.core.repo.session"
}

dependencies {
    implementation(projects.core.repo.session.api)
    implementation(libs.androidx.datastore)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
}
