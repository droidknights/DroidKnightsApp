plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.player"
}

dependencies {
    implementation(projects.core.playback)
    implementation(libs.coroutines.guava)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.media3.player)
    implementation(libs.androidx.media3.player.session)
}
