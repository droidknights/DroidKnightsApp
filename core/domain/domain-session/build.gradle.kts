plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(projects.core.data.dataSessionApi)
            implementation(projects.core.domain.domainSessionApi)
            implementation(projects.core.model.modelSession)
        }
    }
}

android.namespace = "com.droidknights.app.core.domain.session"
