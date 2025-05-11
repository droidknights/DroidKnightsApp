import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.setting")
}

dependencies {
    implementation(libs.androidx.appcompat)

    implementation(libs.oss.licenses)
}
