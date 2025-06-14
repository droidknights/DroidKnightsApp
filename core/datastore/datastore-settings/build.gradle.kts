import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

setNamespace("core.datastore.settings")

dependencies {
    implementation(projects.core.datastore.datastoreSettingsApi)

    implementation(libs.androidx.datastore)

    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
}