plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.home"
}

dependencies {

    implementation(projects.feature.homeApi)
    implementation(projects.feature.mainNavGraph)

    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}
