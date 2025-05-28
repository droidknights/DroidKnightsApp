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
        androidMain.dependencies {
            implementation(libs.bundles.datastore)
        }
        iosMain.dependencies {
            implementation(libs.bundles.datastore)
        }
        desktopMain.dependencies {
            implementation(libs.bundles.datastore)
        }
    }
}

android.namespace = "com.droidknights.app.core.datastore.session"
