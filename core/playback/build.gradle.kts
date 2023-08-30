plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.playback"
}

dependencies {
    implementation(projects.core.model)
}
