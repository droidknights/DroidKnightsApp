plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.main"
}

dependencies {
    implementation(projects.feature.mainNavGraph)
    implementation(projects.feature.homeApi)
    implementation(projects.feature.settingApi)
    implementation(projects.feature.contributorApi)
    implementation(projects.feature.sessionApi)
    implementation(projects.feature.bookmarkApi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
