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
    implementation(libs.androidx.runner)
    implementation(libs.hilt.android.testing)
}
