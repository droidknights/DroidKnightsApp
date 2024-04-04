import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.feature")
}

android {
    setNamespace("feature.contributor")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.kotlinx.immutable)
}
