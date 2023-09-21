plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.bookmark"
}

dependencies {
    implementation(projects.core.repo.session.api)

    implementation(projects.feature.bookmarkApi)
    implementation(projects.feature.mainNavGraph)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
}
