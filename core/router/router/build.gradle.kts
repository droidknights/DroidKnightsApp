import com.droidknights.app.configureComposeFeature
import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.router")
configureComposeFeature()

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.navigation)
    implementation(libs.hilt.navigation.compose)

    implementation(projects.core.router.routerApi)
}
