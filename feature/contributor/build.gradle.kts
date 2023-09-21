plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.contributor"
}

dependencies {
    implementation(projects.core.repo.contributor.api)

    implementation(projects.feature.contributorApi)
    implementation(projects.feature.mainNavGraph)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.kotlinx.immutable)
}
