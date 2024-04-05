import com.droidknights.app.setNamespace

plugins {
    id("droidknights.android.feature")
}

android {
    setNamespace("feature.session")
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(projects.widget)
}
