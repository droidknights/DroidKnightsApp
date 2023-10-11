plugins {
    id("droidknights.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "com.droidknights.app2023.core.playback"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(libs.coroutines.guava)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.media3.player)
    implementation(libs.androidx.media3.player.session)
    implementation(libs.androidx.media3.player.dash)
}
