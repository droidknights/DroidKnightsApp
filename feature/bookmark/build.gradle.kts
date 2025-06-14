import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.bookmark")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)

    implementation(projects.core.domain.domainSessionApi)

    // 라우팅을 위한 api
    implementation(projects.feature.bookmarkApi)
    implementation(projects.core.router.routerApi)

    // 라우팅이 필요한 화면
    implementation(projects.feature.sessionApi)
}
