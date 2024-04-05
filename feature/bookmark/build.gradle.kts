import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.feature")
}

android {
    setNamespace("feature.bookmark")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
}
