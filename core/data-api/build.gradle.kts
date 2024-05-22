import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
    id("kotlinx-serialization")
}

android {
    setNamespace("core.data.api")
}

dependencies {
    implementation(projects.core.model)
}
