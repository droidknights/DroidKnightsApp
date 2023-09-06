plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
}

android {
    namespace = "com.droidknights.app2023.core.domain"
}

dependencies {
    implementation(projects.core.dataApi)
    implementation(projects.core.domainApi)
    implementation(projects.core.model)

    implementation(libs.inject)
}
