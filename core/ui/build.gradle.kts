plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.core.ui"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
}
