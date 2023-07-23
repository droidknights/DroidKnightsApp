plugins {
    id("droidknights.android.feature")
}

android {
    namespace = "com.droidknights.app2023.feature.main"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:contributor"))
    implementation(project(":feature:session"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
}
