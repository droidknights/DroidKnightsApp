plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    namespace = "com.droidknights.app2023.widget"
}

dependencies {
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)

    implementation(projects.core.designsystem)

    implementation(projects.core.domain)
    implementation(project(mapOf("path" to ":core:model")))
}