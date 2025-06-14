import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.session")
}

dependencies {
    implementation(projects.feature.sessionApi)
    implementation(projects.core.router.routerApi)

    implementation(libs.kotlinx.immutable)
    implementation(projects.widget)
    implementation(projects.core.domain.domainSessionApi)
    implementation(projects.core.router.routerApi)
}
