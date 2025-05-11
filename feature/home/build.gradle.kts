import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.home")
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)

    implementation(projects.core.domain.domainSponsorApi)
}
