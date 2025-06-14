import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.setting")
}

dependencies {
    implementation(projects.feature.settingApi)

    implementation(libs.androidx.appcompat)

    implementation(libs.oss.licenses)

    implementation(projects.core.action.actionApi)
    implementation(projects.core.data.dataSettingsApi)
}
