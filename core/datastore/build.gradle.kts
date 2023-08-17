plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.datastore"
}

dependencies {
    implementation(libs.androidx.datastore)
}
