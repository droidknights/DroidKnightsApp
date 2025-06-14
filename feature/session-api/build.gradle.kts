plugins {
    id("droidknights.feature")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.core.router.routerApi)
        }
    }
}

android.namespace = "com.droidknights.app.feature.session.api"
