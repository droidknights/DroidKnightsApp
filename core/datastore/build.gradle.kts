import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.datastore")
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
