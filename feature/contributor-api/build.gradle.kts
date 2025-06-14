import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

android {
    setNamespace("feature.contributor.api")
}

dependencies {
    implementation(projects.core.router.routerApi)
}
