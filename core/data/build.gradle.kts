import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.library")
    id("droidknights.android.hilt")
    id("kotlinx-serialization")
}

android {
    setNamespace("core.data")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.dataApi)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.turbine)
}
