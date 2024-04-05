import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    setNamespace("widget")
}

dependencies {
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.glance.tools.appwidget.host)

    implementation(projects.core.designsystem)

    implementation(projects.core.domain)
    implementation(projects.core.model)
}