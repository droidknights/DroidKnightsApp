plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model.modelContributor)

            api(libs.kotlinx.coroutines.core)
        }
    }
}

android.namespace = "com.droidknights.app.core.domain.contributor.api"
