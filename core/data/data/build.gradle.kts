import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.data")

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.data.dataApi)

    implementation(projects.core.network.networkApi)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.turbine)
}
