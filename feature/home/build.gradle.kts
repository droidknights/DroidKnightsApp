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

    // 라우팅을 위한 api
    implementation(projects.feature.homeApi)
    implementation(projects.core.router.routerApi)

    // 라우팅 타겟 api
    implementation(projects.feature.sessionApi)
    implementation(projects.feature.contributorApi)

    implementation(projects.core.domain.domainSponsorApi)
}
