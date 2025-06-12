plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(projects.core.data.dataContributorApi)
            implementation(projects.core.domain.domainContributorApi)
            implementation(projects.core.model.modelContributor)
        }
    }
}

android.namespace = "com.droidknights.app.core.domain.contributor"
