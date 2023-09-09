plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.core.ui"
}

dependencies {
    implementation(projects.core.repo.session.api)
    implementation(projects.core.designsystem)
}
