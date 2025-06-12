plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain.domainContributorApi)
            implementation(projects.core.model.modelContributor)
            implementation(libs.kotlinx.immutable)
        }
    }
}

android.namespace = "com.droidknights.app.feature.contributor"
