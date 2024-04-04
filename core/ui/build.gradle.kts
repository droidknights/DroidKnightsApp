import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    setNamespace("core.ui")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}
