plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(projects.core.datastore.datastoreSessionApi)
            implementation(projects.core.datastore.datastoreCore)
        }
    }
}

android.namespace = "com.droidknights.app.core.datastore.session"
