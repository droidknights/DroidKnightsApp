import com.droidknights.app.configureComposeFeature
import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
}

setNamespace("core.action")
configureComposeFeature()

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.hilt.navigation.compose)

    implementation(projects.core.action.actionApi)
}
