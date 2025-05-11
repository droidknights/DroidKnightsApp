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
}
