import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
}

android {
    setNamespace("core.testing")
}

dependencies {
    api(libs.junit4)
    api(libs.junit.vintage.engine)
    api(libs.kotlin.test)
    api(libs.mockk)
    api(libs.turbine)
    api(libs.coroutines.test)
    api(libs.roborazziRule)
    api(libs.androidx.compose.ui.test)
    implementation(libs.hilt.android.testing)
    implementation(libs.androidx.compose.ui.test)
    implementation(libs.roborazzi)
    implementation(libs.androidx.runner)
    implementation(projects.core.designsystem)
    implementation(projects.feature.home)
}
