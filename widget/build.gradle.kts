import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
    alias(libs.plugins.droidknights.android.compose)
}

android {
    setNamespace("widget")
}

dependencies {
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.glance.tools.appwidget.host)

    implementation(projects.core.designsystem)

    implementation(projects.core.domain.domainSessionApi)
}