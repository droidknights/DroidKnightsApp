plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.playback"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.media3.player)
    implementation(libs.androidx.media3.player.session)
    implementation(libs.androidx.media3.player.dash)
}
