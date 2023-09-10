plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.session"
}

dependencies {
    implementation(projects.feature.sessionApi)
    implementation(projects.feature.mainNavGraph)

    implementation(libs.kotlinx.immutable)
}
