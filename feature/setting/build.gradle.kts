plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.setting"
}

dependencies {
    implementation(projects.feature.settingApi)
    implementation(projects.feature.mainNavGraph)

    implementation(libs.androidx.appcompat)

    implementation(libs.oss.licenses)
}
