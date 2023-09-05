plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
}

android {
    namespace = "com.droidknights.app2023.core.datastore"
}

dependencies {
    implementation(projects.core.datastoreApi)
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
