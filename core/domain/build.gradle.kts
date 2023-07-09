plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.domain"
}

dependencies {
    implementation(project(":core:data"))

    implementation(libs.inject)
}
