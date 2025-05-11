import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain")
}

dependencies {
    implementation(projects.core.dataApi)
    implementation(projects.core.model)

    implementation(libs.inject)
}
