import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

setNamespace("core.datastore.session")

dependencies {
    implementation(projects.core.datastore.datastoreSessionApi)

    implementation(libs.androidx.datastore)

    testImplementation(libs.kotlin.test)
}
