import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    id("kotlinx-serialization")
}

android {
    setNamespace("core.data.api")
}

dependencies {
    implementation(projects.core.model)
}
