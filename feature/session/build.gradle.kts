plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain.domainSessionApi)
            implementation(projects.core.model.modelSession)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.immutable)
        }
    }
}

android.namespace = "com.droidknights.app.feature.session"
