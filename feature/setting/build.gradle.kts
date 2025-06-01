import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.setting")
}

dependencies {
    implementation(projects.core.router.routerApi)

    implementation(libs.androidx.appcompat)

    implementation(libs.oss.licenses)

    implementation(projects.core.action)
    implementation(projects.core.data.dataSettingsApi)
}
