plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.datastore"
}

dependencies {
    api(libs.junit4)
    api(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
