import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.feature)
}

android {
    setNamespace("feature.main")

    defaultConfig {
        testInstrumentationRunner =
            "com.droidknights.app.core.testing.runner.DroidKnightsTestRunner"
    }
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.homeApi)
    implementation(projects.feature.setting)
    implementation(projects.feature.settingApi)
    implementation(projects.feature.contributor)
    implementation(projects.feature.bookmark)
    implementation(projects.feature.bookmarkApi)
    implementation(projects.feature.session)
    implementation(projects.feature.sessionApi)
    androidTestImplementation(projects.core.testing)
    debugImplementation(projects.core.uiTestHiltManifest)

    implementation(projects.core.router.router)
    implementation(projects.core.router.routerApi)

    implementation(projects.widget)
    implementation(projects.core.data.dataContributorApi)
    implementation(projects.core.data.dataSettingsApi)
    implementation(projects.core.model.modelSession)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
}
