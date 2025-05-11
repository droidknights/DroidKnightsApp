import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain.contributor.api")
}

dependencies {
    implementation(projects.core.model)
}
