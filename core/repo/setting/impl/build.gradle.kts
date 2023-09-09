plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.droidknights.app2023.core.repo.setting"
}

dependencies {
    implementation(projects.core.repo.setting.api)

    implementation(libs.androidx.datastore)

    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
}
