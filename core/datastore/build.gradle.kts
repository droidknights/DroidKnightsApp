plugins {
    id("droidknights.android.library")
}

android {
    namespace = "com.droidknights.app2023.core.datastore"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
