import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.data.sponsor")

dependencies {
    implementation(projects.core.data.dataSponsorApi)

    implementation(projects.appConfig.appConfigApi)

    implementation(projects.core.network.networkApi)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.datetime)
}
