plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.inject)
}
