import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.compose)
}

android {
    setNamespace("core.ui")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}
