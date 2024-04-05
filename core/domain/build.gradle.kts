import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
}

android {
    setNamespace("core.domain")
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.inject)
}
