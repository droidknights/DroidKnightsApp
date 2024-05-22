import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.feature")
}

android {
    setNamespace("feature.main")
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.setting)
    implementation(projects.feature.contributor)
    implementation(projects.feature.session)
    implementation(projects.feature.bookmark)

    implementation(projects.widget)
    implementation(projects.core.dataApi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
