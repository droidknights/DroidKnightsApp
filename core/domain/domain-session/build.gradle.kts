import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain.session")
}

dependencies {
    implementation(projects.core.domain.domainSessionApi)

    implementation(projects.core.data.dataSessionApi)

    implementation(libs.inject)
}
