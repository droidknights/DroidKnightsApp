plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.testing"
}

dependencies {
    api(libs.junit4)
    api(libs.junit.vintage.engine)
    api(libs.kotlin.test)
    api(libs.mockk)
    api(libs.turbine)
    api(libs.coroutines.test)
}
