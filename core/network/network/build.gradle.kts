import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.network")

dependencies {
    implementation(projects.core.network.networkApi)
    implementation(projects.appConfig.appConfigApi)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
