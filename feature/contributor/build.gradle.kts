import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.contributor")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.kotlinx.immutable)

    implementation(projects.feature.contributorApi)
    implementation(projects.core.router.routerApi)

    implementation(projects.core.domain.domainContributorApi)
}
