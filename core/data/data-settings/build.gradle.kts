import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.android.hilt)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.data.settings")

dependencies {
    implementation(projects.core.data.dataSettingsApi)

    implementation(projects.core.datastore.datastoreSettingsApi)
}
